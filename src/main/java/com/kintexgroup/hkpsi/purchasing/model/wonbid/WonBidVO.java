package com.kintexgroup.hkpsi.purchasing.model.wonbid;

import lombok.Data;

/**
 * @author lmao
 * @since 2020/9/9 09:45
 */
@Data
public class WonBidVO {


    /**
     * 中标号 展示用
     */
    private String number;

    /**
     * 中标日期
     */
    private String date;

    /**
     * 中标金额
     */
    private Double amount;

    /**
     * 投标 ID
     */
    private String bidId;

    /**
     * 供应商
     */
    private String vendor;

    /**
     * 供应商仓库
     */
    private String warehouse;

    /**
     * 中标汇率
     */
    private Double exchangeRate;

    private Integer isShipped;
}
