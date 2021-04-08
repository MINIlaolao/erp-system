package com.kintexgroup.hkpsi.purchasing.model;

import lombok.Data;

/**
 * @author LMAO
 * @since 2020/12/5 15:51
 */
@Data
public class PaymentRecordVO {
    /**
     * 中标单号
     */
    private String wonNumber;

    /**
     * 付款日期
     */
    private String payDate;

    /**
     * 应付金额
     */
    private String amountPayable;

    /**
     * 使用余额
     */
    private String useBalance;

    /**
     * 实付金额
     */
    private String amountPaid;

    private Double exchangeRate;
}


