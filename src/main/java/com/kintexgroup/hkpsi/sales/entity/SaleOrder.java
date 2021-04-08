package com.kintexgroup.hkpsi.sales.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lmao
 * @since 2020/9/27 16:06
 */
@Data

public class SaleOrder implements Serializable {

    /**
     * 订单编号
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 客户id
     */
    @Column(name = "client_id")
    private Integer clientId;

    /**
     * 总价
     */
    @Column(name = "count_price")
    private Double countPrice;

    /**
     * 折扣
     */
    @Column(name = "discount")
    private Double handingCharge;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 定金
     */
    @Column(name = "deposit")
    private Double deposit;

    /**
     * 其他收费
     */
    @Column(name = "other_price")
    private Double otherPrice;

    /**
     * 结款时间
     */
    @Column(name = "pay_money_date")
    private Date payMoneyDate;

    /**
     * 销售人
     */
    @Column(name = "salesman")
    private String salesman;

    /**
     * 销售时间
     */
    @Column(name = "`date`")
    private Date date;

    @Column(name = "exchange_rate")
    private Double exchangeRate;

    @Column(name = "disabled")
    private Boolean disabled;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    private static final long serialVersionUID = 1L;
}