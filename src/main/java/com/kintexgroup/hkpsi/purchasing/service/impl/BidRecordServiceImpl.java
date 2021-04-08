package com.kintexgroup.hkpsi.purchasing.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.purchasing.dao.BidDao;
import com.kintexgroup.hkpsi.purchasing.dao.BidRecordDao;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordVO;
import com.kintexgroup.hkpsi.purchasing.service.BidRecordService;
import com.kintexgroup.hkpsi.purchasing.util.BidRecordPojoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author pengli
 * @author lmao
 */
@Service("BidListService")
@Transactional(rollbackFor = BusinessException.class)
@Slf4j
public class BidRecordServiceImpl implements BidRecordService {

    @Resource
    private BidRecordDao bidRecordDao;

    @Resource
    private BidDao bidDao;

    @Override
    public boolean deleteOneById(String id) {
        if (bidRecordDao.deleteOneById(id)) {
            return true;
        }
        throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
    }

    @Override
    public BidRecordVO selectOneById(String id) {
        if (bidRecordDao.selectOneById(id) == null) {
            throw new BusinessException(ResponseCode.DB_ID_NULL_ERROR);
        }
        return BidRecordPojoConverter.fromBidList(bidRecordDao.selectOneById(id));
    }

    @Override
    public PageInfo<BidRecordVO> list(BidRecordPageDTO args) {
        PageMethod.startPage(args.getCurrent(), args.getPageSize(), true);
        var list = new ArrayList<BidRecordVO>();
        var records = bidRecordDao.list(args);

        final String bidId = records.listIterator().next().getBidId();

        final String currencySymbol = bidDao.selectCurrencySymbolByBidId(bidId);

        records.forEach(record -> list.add(BidRecordPojoConverter.fromBidRecord(record, currencySymbol)));
        return CommonPageConverter.toPageInfo(records, list);
    }

    /**
     * 根据 BidRecord 信息匹配我们自己的 Sku
     *
     * @param sqlEntity bidRecord
     * @return 我们自己的 SkuId
     */
//    private String matchSku(BidRecordEntity sqlEntity) {
//        var attributeList = skuDao
//            .selectAttributeByModelName(sqlEntity.getModelName());
//        AttributeId matchAttribute = new AttributeId();
//        matchAttribute.setColor(sqlEntity.getColor());
//        matchAttribute.setCapacity(sqlEntity.getCapacity());
//        matchAttribute.setModel(sqlEntity.getModel());
//        matchAttribute.setCarrier(sqlEntity.getCarrier());
//        attributeList.forEach(attribute -> {
//            boolean isMatch = attribute.equals(matchAttribute);
//            if (isMatch) {
//                sqlEntity.setSkuId(attribute.getId());
//            }
//        });
//        var matchSkuId = sqlEntity.getSkuId();
//        if (matchSkuId == null) {
//            log.info(sqlEntity.getDescription());
//            throw new BusinessException(ResponseCode.FAIL);
//        }
//        return matchSkuId;
//    }


}
