package com.kintexgroup.hkpsi.purchasing.util;

import com.kintexgroup.hkpsi.purchasing.entity.BidRecordEntity;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordDTO;
import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordVO;

/**
 * @author pengli
 */
public final class BidRecordPojoConverter {
    /**
     * 将 从接口请求传入的参数类 转换为实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static BidRecordEntity toEntity(BidRecordDTO dto) {
        var entity = new BidRecordEntity();
        entity.setBidId(dto.getBidId());
        entity.setBidQty(dto.getBidQty());
        entity.setBidPrice(dto.getBidPrice());
        entity.setBidHkd(dto.getBidHkd());
        entity.setBidHigh(dto.getBidHigh());
        entity.setTag(dto.getTag());
        entity.setSourceWarehouse(dto.getWarehouse());
        entity.setSourceSKU(dto.getSku());
        entity.setSourceOEM(dto.getBrand());
        entity.setSourceDescription(dto.getDescription());
        entity.setSourceModelNumber(dto.getModelNumber());
        entity.setSourceModelName(dto.getModelName());
        entity.setSourceModel(dto.getModel());
        entity.setSourceCapacity(dto.getCapacity());
        entity.setSourceCarrier(dto.getCarrier());
        entity.setSourceColor(dto.getColor());
        entity.setSourceCondition(dto.getCondition());
        entity.setSourceGrade(dto.getGrade());
        entity.setSourceFMiPLocked(dto.getFMiPLocked());
        entity.setSourceSIMLocked(dto.getSIMLocked());
        entity.setSourceLCDHealth(dto.getLCDHealth());
        return entity;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static BidRecordEntity toCreateEntity(BidRecordDTO dto, int creator) {
        var entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param updater 更新人的 ID
     * @param dto     参数类
     * @return 实体类
     */
    public static BidRecordEntity toUpdateEntity(BidRecordDTO dto, int updater) {
        BidRecordEntity entity = toEntity(dto);
        entity.setWonId(dto.getWonId());
        entity.setWonQty(dto.getWonQty());
        entity.setWonPrice(dto.getWonPrice());
        entity.setWonHkd(dto.getWonHkd());
        entity.setUpdatedBy(updater);
        return entity;
    }

    /**
     * 将从数据库获取到的实体类 转为发送给前端的视图类
     *
     * @param bidList 实体类
     * @return 视图类
     */
    public static BidRecordVO fromBidList(BidRecordEntity bidList) {
        BidRecordVO vo = new BidRecordVO();
        vo.setId(bidList.getId());
        vo.setBidId(bidList.getBidId());
        vo.setWonId(bidList.getWonId());
        vo.setBidQty(bidList.getBidQty());
        vo.setWonQty(bidList.getWonQty());
        vo.setBidPrice(bidList.getBidPrice());
        vo.setBidHkd(bidList.getBidHkd());
        vo.setWonPrice(bidList.getWonPrice());
        vo.setWonHkd(bidList.getWonHkd());
        return vo;
    }

    public static BidRecordVO fromBidRecord(BidRecordEntity entity, String currencySymbol) {
        var vo = new BidRecordVO();
        vo.setId(entity.getId());
        vo.setBidQty(entity.getBidQty());

        var bidPrice = entity.getBidPrice();
        var wonPrice = entity.getWonPrice();
        //添加货币符号
//        if (bidPrice != null) {
//            String result = currencySymbol + bidPrice.toString();
//            vo.setBidPrice(result);
//        }
//        if (wonPrice != null) {
//            String result = currencySymbol + wonPrice.toString();
//            vo.setWonPrice(result);
//        }
        return vo;
    }

    /**
     * 将容量转换成int类型
     *
     * @param capacity 容量
     * @return int
     */
    public static Integer toBidRecordCapacity(String capacity) {
        String nullInput = "-";
        if (nullInput.equals(capacity)) {
            capacity = "0";
        }
        capacity = capacity.trim().replace("G", "");
        return Integer.valueOf(capacity);
    }

}
