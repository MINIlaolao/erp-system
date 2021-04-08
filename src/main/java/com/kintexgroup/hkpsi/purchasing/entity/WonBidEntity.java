package com.kintexgroup.hkpsi.purchasing.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author lmao
 * @since 2020/9/8 18:24
 */
@Data
public class WonBidEntity {

    /**
     * 中标ID 内部用
     */
    private String id;

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
     * 是否已经导入 IMEI List
     */
    private Boolean stage;

    /**
     * 创建时间 数据表自动生成
     */
    private Date createdAt;

    /**
     * 创建人ID
     */
    private Integer createdBy;

    /**
     * 修改时间 数据表自动生成
     */
    private Date updatedAt;

    /**
     * 修改人ID
     */
    private Integer updatedBy;

    /**
     * 与投标关联
     */
    private BidEntity bid;


    /**
     * 中标汇率
     */
    private Double exchangeRate;

    private Integer isShipped;
}
