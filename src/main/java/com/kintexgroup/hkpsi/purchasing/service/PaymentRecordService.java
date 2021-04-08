package com.kintexgroup.hkpsi.purchasing.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordDTO;
import com.kintexgroup.hkpsi.purchasing.model.PaymentRecordVO;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/4 11:47
 */
public interface PaymentRecordService {

    /**
     * 更新一条投标记录
     *
     * @param dto dto
     * @return 是否成功
     */
    boolean updatePaymentRecord(PaymentRecordDTO dto);

    /**
     * 批量更新投标记录
     *
     * @param dtos dtolist
     */
    void batchUpdatePaymentRecord(List<PaymentRecordDTO> dtos);

    /**
     * 根据条件查询一条或多条记录
     *
     * @param paymentRecord 条件
     * @return list
     */
    PageInfo<PaymentRecordVO> selectOneOrMany(PaymentRecord paymentRecord);

}
