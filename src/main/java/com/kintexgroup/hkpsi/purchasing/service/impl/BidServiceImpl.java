package com.kintexgroup.hkpsi.purchasing.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Strings;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.purchasing.dao.BidDao;
import com.kintexgroup.hkpsi.purchasing.dao.BidRecordDao;
import com.kintexgroup.hkpsi.purchasing.entity.BidRecordEntity;
import com.kintexgroup.hkpsi.purchasing.model.BidDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidDetailVO;
import com.kintexgroup.hkpsi.purchasing.model.BidPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.BidVO;
import com.kintexgroup.hkpsi.purchasing.service.BidService;
import com.kintexgroup.hkpsi.purchasing.util.BidPojoConverter;
import com.kintexgroup.hkpsi.purchasing.util.BidRecordPojoConverter;
import org.apache.ibatis.type.TypeException;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.kintexgroup.hkpsi.common.util.ExceptionUtil.isUpdateSuccess;

/**
 * @author junhangs
 * @since 2020/8/23 22:38
 */
@Service("BidService")
@Transactional(rollbackFor = BusinessException.class)
public class BidServiceImpl implements BidService {

    @Resource
    private BidDao bidDao;

    @Resource
    private BidRecordDao bidRecordDao;

//    @Resource
//    private VendorSkuService vendorSkuService;
//    
//    @Resource
//    private VendorSkuDao vendorSkuDao;

    /**
     * 插入一条新的投标号，插入多条投标记录
     *
     * @param args 插入的投标信息
     * @return 一条 Bid 记录
     */
    @Override
    public String create(BidDTO args) {
        var user = ContextHolderUtil.getAuthedUserId();

        var bEntity = BidPojoConverter.toCreateEntity(args, user);

        bidDao.insertOne(bEntity);

        var bRecords = args.getRecords();

        int initialCapacity = bRecords.size();

        var brEntities = new ArrayList<BidRecordEntity>(initialCapacity);

//        var tags = new ArrayList<String>(initialCapacity); // 用于获取对应 vendor sku id

        for (var bRecord : bRecords) {
            var brEntity = BidRecordPojoConverter.toCreateEntity(bRecord, user);

            brEntity.setBidId(bEntity.getId()); // 设置投标编号

            brEntities.add(brEntity);
        }
        // 先处理 vendor sku
//        var vkEntities = vendorSkuService.obtainNewVendorSkuList(bEntity.getVendor(), bRecords);
//        vendorSkuDao.insertBatch(vkEntities);

        // 为 record 找到对应的 vendor sku id
//        vendorSkuService.obtainMapOfIdTag(tags).forEach((k, v) -> brEntities.forEach(e -> {
//            if (e.getTag().equals(v)) {
//                e.setVendorSkuId(k);
//            }
//        }));

        bidRecordDao.insertBatch(brEntities);

        return bEntity.getId();
    }

    /**
     * 根据 id 删除一条投标信息
     *
     * @param id bidId
     * @return Success :true || Fail  :false
     */
    @Override
    public boolean delete(String id) {
        return bidDao.deleteOne(id);
    }

    /**
     * 根据 id 查询一条投标信息
     *
     * @param id bidId
     * @return 一条 Bid 记录
     */
    @Override
    public BidVO selectOne(String id) {
        return bidDao.selectOne(id);
    }

    /**
     * 根据 id 更新一条投标信息
     *
     * @param dto 更新的投标信息
     * @param id  bidId
     * @return Success :true || Fail  :false
     */
    @Override
    public BidVO update(String id, BidDTO dto) {
        var entity = BidPojoConverter.toUpdateEntity(id, dto, ContextHolderUtil.getAuthedUserId());
        try {
            /*checkState(bidDao
                    .checkDuplicate(entity.getId()),
                ResponseCode.DB_UPDATE_DUPLICATE_ERROR.getMessage());*/
            isUpdateSuccess(bidDao.updateOne(entity));
            return selectOne(id);
        } catch (MyBatisSystemException | TypeException e) {
            //捕获更新时 id 不匹配的异常
            throw new BusinessException(e.getCause(), ResponseCode.DB_TYPE_ERROR);
        }
    }

    @Override
    public BidDetailVO getBidDetailById(String id) {
        var vo = bidDao.selectOneDetail(id);
        var wonMap = vo.getWon();

        String isArrival = "isArrival";
        boolean expression1 = wonMap.get(isArrival) == null;
        wonMap.put(isArrival, !expression1);
        String isShipped = "isShipped";
        boolean expression2 = wonMap.get(isShipped) == null;
        wonMap.put(isShipped, !expression2);
        String isPayment = "isPayment";
        boolean expression3 = "1970-01-01".equals(wonMap.get(isPayment));
        wonMap.put(isPayment, !expression3);

        vo.setWon(wonMap);
        return vo;
    }

    @Override
    public List<BidDetailVO> getBidDetailList() {
        return bidDao.selectManyDetail();
    }


    /**
     * 查询所有 Bid
     *
     * @return 所有 Bid 记录
     */
    @Override
    public List<BidVO> selectAll() {
        return bidDao.selectAll();
    }

    @Override
    public PageInfo<BidVO> list(BidPageDTO dto) {
        PageMethod.startPage(dto.getCurrent(), dto.getPageSize(), true);
        var records = bidDao.selectBatch(dto);
        var list = new ArrayList<BidVO>(records);
        return CommonPageConverter.toPageInfo(records, list);
    }


    /**
     * 投标编号 年月日+三位数，例如 '20201112001'：2020 年 11 月 12 日第一单
     *
     * @return 新的 bidNumber
     */
    @Override
    public String generateNewBidId(String date) {
        var bid = bidDao.selectCreatedAt(date);
        String result;
        // bid不为空，说明这一单不是今天的第一单
        if (bid != null) {
            // 取出 id 的后三位并转为 int 类型
            int num = Integer.parseInt(bid.getId().substring(8, 11));
            num++;
            result = Strings.padStart(num + "", 3, '0');
        } else {
            result = "001";
        }
        return date + result;
    }
}
