package com.kintexgroup.hkpsi.purchasing.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.base.Preconditions;
import com.kintexgroup.hkpsi.common.constants.ResponseCode;
import com.kintexgroup.hkpsi.common.exception.BusinessException;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.common.util.ExceptionUtil;
import com.kintexgroup.hkpsi.common.util.KdnUtil;
import com.kintexgroup.hkpsi.information.dao.LogisticsDao;
import com.kintexgroup.hkpsi.information.dao.VendorDao;
import com.kintexgroup.hkpsi.information.entity.Logistics;
import com.kintexgroup.hkpsi.information.model.KdnLogisticsIdName;
import com.kintexgroup.hkpsi.information.model.LogisticsResult;
import com.kintexgroup.hkpsi.information.model.LogisticsResultVO;
import com.kintexgroup.hkpsi.purchasing.dao.BidDao;
import com.kintexgroup.hkpsi.purchasing.dao.BidRecordDao;
import com.kintexgroup.hkpsi.purchasing.dao.PaymentRecordDao;
import com.kintexgroup.hkpsi.purchasing.dao.WonBidDao;
import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import com.kintexgroup.hkpsi.purchasing.entity.WonBidEntity;
import com.kintexgroup.hkpsi.purchasing.model.ShipDTO;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordPageDTO;
import com.kintexgroup.hkpsi.purchasing.model.wonbid.*;
import com.kintexgroup.hkpsi.purchasing.service.WonBidService;
import com.kintexgroup.hkpsi.purchasing.util.WonBidPojoConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.kintexgroup.hkpsi.common.util.ExceptionUtil.isInsertSuccess;
import static com.kintexgroup.hkpsi.common.util.ExceptionUtil.isUpdateSuccess;

/**
 * @author lmao
 * @since 2020/9/9 09:52
 */
@Service("WonBidService")
@Transactional(rollbackFor = BusinessException.class)
public class WonBidServiceImpl implements WonBidService {

    @Resource
    private WonBidDao wonBidDao;
    @Resource
    private BidRecordDao bidRecordDao;
    @Resource
    private BidDao bidDao;
    @Resource
    private PaymentRecordDao paymentRecordDao;
    @Resource
    private LogisticsDao logisticsDao;
    @Resource
    private VendorDao vendorDao;

    private static final String ZERO = "0";


    @Override
    public void create(WonBidDTO args) throws ParseException {
        // 时间判断，不允许中标时间早于投标时间
        if (isDateIsEarlyBidDate(args)) {
            throw new BusinessException(ResponseCode.BIZ_WON_DATE_IS_EARLY_THAN_BID_DATE);
        }
        var user = ContextHolderUtil.getAuthedUserId();
        var entity = WonBidPojoConverter.toCreateEntity(args, user);
        //新建 won bid 记录
        var bidId = args.getBidId();
        var wonId = args.getNumber();
        var tagUnitPrices = args.getRecords();

        final double exchangeRate = bidDao.selectOne(bidId).getExchangeRate();

        entity.setExchangeRate(exchangeRate);
        
        //全量更新
        //投标价即中标价
        if (tagUnitPrices.size() == 0) {
            //查询记录表对应的 bidRecord
            var param = new BidRecordPageDTO();
            param.setBidId(bidId);

            var records = bidRecordDao.list(param);
            // 获取投标信息进行更新
            records.forEach(record -> {
                var id = record.getWonId();
                if (id.isEmpty() || ZERO.equals(id)) {
                    record.setWonId(wonId);
                    record.setWonPrice(record.getBidPrice());
                    record.setWonHkd(record.getWonHkd());
                    record.setWonQty(record.getWonQty());
                    record.setWonPrice(record.getBidSum());
                } else {
                    throw new BusinessException(ResponseCode.BIZ_BIDS_AWARDED);
                }
            });
            // 更新中标信息
            ExceptionUtil.isUpdateSuccess(bidRecordDao.updateMany(bidId, user, records));
        } else {
            /* TODO 待明确excel格式 部分更新,依靠 tag*/
//            List<String> tags = new ArrayList<>();
//            tagUnitPrices.forEach(tagUnitPrice -> tags.add(tagUnitPrice.getTag()));
//            var dtoBidRecords = bidRecordDao.selectBatchByTag(bidId, tags);
//            dtoBidRecords.forEach(bidRecord -> {
//                var id = bidRecord.getWonId();
//                boolean isWonIdBlank = wonId == null || wonId.isEmpty() || ZERO.equals(wonId);
//                if (isWonIdBlank) {
//                    tagUnitPrices.forEach(tagUnitPriceRecord -> {
//                        if (tagUnitPriceRecord.getTag().equals(bidRecord.getTag())) {
//                            bidRecord.setWonId(id);
//                            bidRecord.setWonPrice(tagUnitPriceRecord.getUnitPrice());
//                            bidRecord.setWonQty(tagUnitPriceRecord.getQuantity());
//                        }
//                    });
//                } else {
//                    throw new BusinessException(ResponseCode.BIZ_BIDS_AWARDED);
//                }
//            });

//            ExceptionUtil.isUpdateSuccess(
//                bidRecordDao
//                    .updateManyByTag(tags, ContextHolderUtil.getAuthedUserId(), dtoBidRecords));
        }

        isInsertSuccess(wonBidDao.insertOne(entity));
        isInsertSuccess(paymentRecordDao.insertSelective(addPaymentRecord(entity)));
    }


    private PaymentRecord addPaymentRecord(WonBidEntity wonBid) {
        PaymentRecord paymentRecord = new PaymentRecord();
        paymentRecord.setWonNumber(wonBid.getNumber());
        paymentRecord.setOperator(ContextHolderUtil.getAuthedUserId());
        paymentRecord.setAmountPayable(wonBid.getAmount());
        return paymentRecord;
    }

    /**
     * 判断中标日期是否早于投标日期
     *
     * @param dto wonBidDto
     * @return 早于:true 相等或晚于:false
     * @throws ParseException
     */
    private boolean isDateIsEarlyBidDate(WonBidDTO dto) throws ParseException {
        var bidDate = bidDao.selectBidDate(dto).getTime();
        var simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        var wonDate = simpleDateFormat.parse(dto.getDate()).getTime();

        return bidDate - wonDate > 0;
    }


    @Override
    public WonBidRecordVO selectOneById(String id) {
        var entity = wonBidDao.selectOneById(id);
        if (entity == null) {
            throw new BusinessException(ResponseCode.DB_TYPE_ERROR);
        }
        var bidRecordList = wonBidDao.selectOneByIdRecord(id);
        if (bidRecordList == null || bidRecordList.isEmpty()) {
            throw new BusinessException(ResponseCode.DB_TYPE_ERROR);
        }
        entity.setBidRecord(bidRecordList);
        return entity;
    }


    @Override
    public List<WonBidVendorVO> selectMany() {
        return wonBidDao.selectMany();
    }


    @Override
    public boolean updateOne(String id, WonBidDTO dto) {
        var entity = WonBidPojoConverter
            .toUpdateEntity(id, dto, ContextHolderUtil.getAuthedUserId());
        isUpdateSuccess(wonBidDao.updateOne(entity));
        return true;
    }


    @Override
    public boolean deleteOneById(String id) {
        return wonBidDao.deleteOneById(id);
    }

    @Override
    public PageInfo<WonBidVO> selectOneOrMany(WinBidPageDTO dto, int page, int size) {
        PageMethod.startPage(page, size, true);
        var list = new ArrayList<WonBidVO>();
        var records = wonBidDao.selectOneOrMany(dto);
        records.forEach(record -> list.add(WonBidPojoConverter.fromEntity(record)));
        return CommonPageConverter.toPageInfo(records, list);
    }

    @Override
    public LogisticsResultVO getLogisticsByWonNumber(String wonNumber) throws Exception {
        //获取物流单号和快递公司代码
        var logisticsNumCodes = logisticsDao.getKdnLogisticsByWonNumber(wonNumber);
        var logisticCode = logisticsNumCodes.getLogisticsNumber();
        var shipperCode = logisticsNumCodes.getKdnLogisticsCode();
        var rawData = KdnUtil.kdnApiLogistics(shipperCode, logisticCode, "8001");
        var shipperName = logisticsDao.selectShipperNameByCode(rawData.getShipperCode());
        return toVO(KdnUtil.wrapperResult(rawData, shipperName));
    }

    private static LogisticsResultVO toVO(LogisticsResult wrapperResult) {
        LogisticsResultVO vo = new LogisticsResultVO();
        vo.setLocation(wrapperResult.getLocation());
        vo.setLogisticCode(wrapperResult.getLogisticCode());
        vo.setShipperCode(wrapperResult.getShipperCode());
        vo.setState(wrapperResult.getState());
        vo.setStateEx(wrapperResult.getStateEx());
        vo.setTraces(wrapperResult.getTraces());
        vo.setSuccess(wrapperResult.getSuccess());
        return vo;
    }

    @Override
    public void setShipperCode(ShipDTO dto) {
        var wonNumber = dto.getWonNumber();
        if (wonBidDao.checkIsShipped(wonNumber) == 1) {
            throw new BusinessException(ResponseCode.BIZ_BID_IS_SHIPPED);
        }

        ExceptionUtil.isUpdateSuccess(wonBidDao.updateShip(wonNumber));

        Logistics logistics = new Logistics();
        logistics.setDisabled(0);
        logistics.setWonNumber(wonNumber);
        logistics.setLogisticsNumber(dto.getLogisticCode());
        logistics.setKdnLogisticsId(dto.getKdnLogisticsId());
        ExceptionUtil.isInsertSuccess(logisticsDao.insertSelective(logistics));
        try {
            getLogisticsByWonNumber(wonNumber);
        } catch (Exception e) {
            throw new BusinessException(ResponseCode.BIZ_LOGISTIC_NUMBER_ERROR);
        }
    }

    @Override
    public List<KdnLogisticsIdName> getLogisticsList() {
        return logisticsDao.getLogisticsIdCodeList();
    }

    @Override
    public String getWonBidNumber(String yearAndMonth, String vendorId) {
        String year = yearAndMonth.substring(0, 4);
        String month = yearAndMonth.substring(4);
        String wonBidCount = wonBidDao.selectWonBidCountByYearVendorId(year, vendorId);
        Preconditions.checkNotNull(wonBidCount, "wonBidCount is null");
        year = year.substring(2);
        String vendorCode = vendorDao.selectCodeById(vendorId);
        Preconditions.checkNotNull(vendorCode, "vendorCode is null");
        String monthCode = switch (month) {
            case "01" -> "J1";
            case "02" -> "F";
            case "03" -> "M1";
            case "04" -> "A1";
            case "05" -> "M2";
            case "06" -> "J2";
            case "07" -> "J3";
            case "08" -> "A2";
            case "09" -> "S";
            case "10" -> "O";
            case "11" -> "N";
            case "12" -> "D";
            default -> throw new IllegalStateException("Unexpected value: " + month);
        };
        return "T" + wonBidCount + year + monthCode + vendorCode;
    }


}
