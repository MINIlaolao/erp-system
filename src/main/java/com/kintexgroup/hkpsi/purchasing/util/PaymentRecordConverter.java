package com.kintexgroup.hkpsi.purchasing.util;

import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordDTO;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordModel;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordVO;

/**
 * @author LMAO
 * @since 2020/12/4 11:55
 */
public class PaymentRecordConverter {
    private PaymentRecordConverter() {
    }

    /**
     * 将 从接口请求传入的参数类 转换为实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static PaymentRecord toEntity(PaymentRecordDTO dto) {
        PaymentRecord entity = new PaymentRecord();
        entity.setUseBalance(dto.getUseBalance());
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setPayDate(dto.getPayDate());
        return entity;
    }

    public static PaymentRecordVO fromEntity(PaymentRecordModel entity) {
        PaymentRecordVO vo = new PaymentRecordVO();
        vo.setUseBalance(entity.getCurrencySymbol() + entity.getUseBalance());
        vo.setAmountPaid(entity.getCurrencySymbol() + entity.getAmountPaid());
        vo.setPayDate(entity.getPayDate());
        vo.setWonNumber(entity.getWonNumber());
        vo.setAmountPayable(entity.getCurrencySymbol() + entity.getAmountPayable());
        vo.setExchangeRate(entity.getExchangeRate());
        return vo;
    }


    /**
     * 用于更新的实体类
     *
     * @param wonNumber 要更新的实体类 wonNumber
     * @param updater   更新人的 ID
     * @param dto       参数类
     * @return 实体类
     */
    public static PaymentRecord toUpdateEntity(String wonNumber, Integer updater, PaymentRecordDTO dto) {
        PaymentRecord entity = toEntity(dto);
        entity.setOperator(updater);
        entity.setWonNumber(wonNumber);
        return entity;
    }
}


