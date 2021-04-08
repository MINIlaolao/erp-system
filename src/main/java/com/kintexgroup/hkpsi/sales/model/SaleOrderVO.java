package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/9/27 5:34 下午
 */
@Data
public class SaleOrderVO {

    /**
     * 订单号
     */
    private String id;

    /**
     * 客户姓名
     */
    private String clientName;

    /**
     * 客户电话
     */
    private String clientNumber;

    /**
     * 销售人
     */
    private String salesman;

    /**
     * 总价
     */
    private Double countPrice;

    /**
     * 折扣
     */
    private Double handingCharge;


    /**
     * 定金
     */
    private Double deposit;


    /**
     * 其他收费
     */
    private Double otherPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 结款时间
     */
    private String payMoneyDate;

    /**
     * 销售时间
     */
    private String date;

    /**
     * 汇率
     */
    private Double exchangeRate;

    /**
     * 货币符号
     */
    private String currencySymbol;
}
