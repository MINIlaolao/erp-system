package com.kintexgroup.hkpsi.purchasing.model;

import lombok.Data;

/**
 * @author junhangs
 * @since 2020/8/23 22:28
 */
@Data
public class BidVO {
    
    /**
     * 投标编号 `bid_id`
     */
    private String id;
    
    /**
     * 投标日期 `bid_date`
     */
    private String bidDate;

    /**
     * 供应商
     */
    private String vendor;

    /**
     * 项目
     */
    private String program;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 货币 `currency`
     */
    private String currency;

    /**
     * 中标汇率
     */
    private Double exchangeRate;

    /**
     * 中标编号 `won_bid_number`
     */
    private String wonNumber;

    /**
     * 中标日期 `won_date`
     */
    private String wonDate;

    /**
     * 中标金额 `amount`
     */
    private Double amount;

}
