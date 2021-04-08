package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/11/13 10:56 上午
 */
@Data
public class ExchangeRateVO {

    /**
     * 汇率id
     */
    private Integer id;

    /**
     * 货币代码
     */
    private String currencyCode;

    /**
     * 货币名称
     */
    private String currencyName;

    /**
     * 货币符号
     */
    private String currencySymbol;

    /**
     * 汇率
     */
    private Double exchangeRate;

    private Integer disabled;
}
