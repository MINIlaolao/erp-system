package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.ArrivalRecord;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordDTO;
import com.kintexgroup.hkpsi.information.model.ArrivalRecordVO;
import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;

/**
 * @author LMAO
 * @since 2020/12/11 10:38
 */
public final class ArrivalRecordPojoConverter {
    private ArrivalRecordPojoConverter() {
    }


    public static ArrivalRecordVO fromEntity(ArrivalRecord entity) {
        var vo = new ArrivalRecordVO();
        vo.setArrivalCarrier(entity.getArrivalCarrier());
        vo.setArrivalDamage(entity.getArrivalDamage());
        vo.setArrivalDate(entity.getArrivalDate());
        vo.setArrivalCartonOrPallet(entity.getArrivalCartonOrPallet());
        vo.setArrivalReceiver(entity.getArrivalReceiver());
        vo.setArrivalDeliveryTime(entity.getArrivalDeliveryTime());
        vo.setArrivalTracking(entity.getArrivalTracking());
        vo.setArrivalWeight(entity.getArrivalWeight());
        vo.setArrivalOrderNumber(entity.getArrivalOrderNumber());
        vo.setArrivalPieces(entity.getArrivalPieces());
        return vo;
    }

    public static ArrivalRecord entity(ArrivalRecordDTO dto) {
        return toEntity(dto);
    }

    private static ArrivalRecord toEntity(ArrivalRecordDTO dto) {
        var entity = new ArrivalRecord();
        entity.setArrivalCarrier(dto.getArrivalCarrier());
        entity.setArrivalDamage(dto.getArrivalDamage());
        entity.setArrivalDate(dto.getArrivalDate());
        entity.setArrivalCartonOrPallet(dto.getArrivalCartonOrPallet());
        entity.setArrivalReceiver(dto.getArrivalReceiver());
        entity.setArrivalDeliveryTime(dto.getArrivalDeliveryTime());
        entity.setArrivalTracking(dto.getArrivalTracking());
        entity.setArrivalWeight(dto.getArrivalWeight());
        entity.setArrivalOrderNumber(dto.getArrivalOrderNumber());
        entity.setArrivalPieces(dto.getArrivalPieces());
        return entity;
    }


    public static ArrivalRecord toCreateEntity(ArrivalRecordDTO dto) {
        var entity = toEntity(dto);
        entity.setCreatedBy(ContextHolderUtil.getAuthedUserId());
        entity.setUpdatedBy(ContextHolderUtil.getAuthedUserId());
        return entity;
    }
}


