package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

/**
 * @author lmao
 * @since 2020/10/13 15:07
 */
@Data
public class GoodsVO {

    private String saleOrder;

    /**
     * 单价
     */
    private Double price;
    private String imei1;
    private String imei2;
    private Integer locked;
    private Integer iCloud;
    private String deviceInTime;
    private String deviceOutTime;
    private Integer disabled;
    private Object attribute;
    private String modelName;
    private String brandName;
    private String category;
    private String model;

    private Integer capacity;

    /**
     * 等级
     */
    private String grade;
    /**
     * 备注
     */
    private String remark;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 汇率
     */
    private Double exchangeRate;
}


