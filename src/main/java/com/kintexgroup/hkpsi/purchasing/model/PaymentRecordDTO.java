package com.kintexgroup.hkpsi.purchasing.model;

import lombok.Data;

/**
 * @author LMAO
 * @since 2020/12/4 11:49
 */
@Data
public class PaymentRecordDTO {
    /**
     * 中标单号
     */
    private String wonNumber;

    /**
     * 付款日期
     */
    private String payDate;

    /**
     * 使用余额
     */
    private Double useBalance;

    /**
     * 实付金额
     */
    private Double amountPaid;

    /**
     * 操作人员
     */
    private String operator;
}


