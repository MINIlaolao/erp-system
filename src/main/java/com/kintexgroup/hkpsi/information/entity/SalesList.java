package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LMAO
 * @since 2020/11/17 13:56
 */
@Data
@NoArgsConstructor
@Table(name = "hkpsi_sales_list")
@NotBlank(message = "SalesList 属性数值缺失")
public class SalesList implements Serializable {
    /**
     * 销售报表id
     */
    @Id
    @Column(name = "sale_list_id")
    private String saleOrderId;

    @Column(name = "won_bid_id")
    private String wonBidId;


    /**
     * 供应商仓库
     */
    @Column(name = "warehouse")
    private String warehouse;

    /**
     * 供应商产品编号
     */
    @Column(name = "item")
    private String item;

    /**
     * 产品标号
     */
    @Column(name = "description")
    private String description;

    /**
     * 品牌
     */
    @Column(name = "brand")
    private String brand;

    /**
     * 型号名称
     */
    @Column(name = "model_name")
    private String modelName;

    /**
     * 型号号码
     */
    @Column(name = "model_number")
    private String modelNumber;

    /**
     * 容量
     */
    @Column(name = "capacity")
    private Integer capacity;

    /**
     * 颜色
     */
    @Column(name = "color")
    private String color;

    /**
     * 运营商
     */
    @Column(name = "carrier")
    private String carrier;

    /**
     * 云端存储
     */
    @Column(name = "icloud")
    private Integer icloud;

    /**
     * SIM卡状态
     */
    @Column(name = "sim_status")
    private Integer simStatus;

    /**
     * 我们的商品等级
     */
    @Column(name = "grade")
    private String grade;

    /**
     * 数量
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 单个成本价
     */
    @Column(name = "cost_price")
    private Double costPrice;

    /**
     * 总成本价
     */
    @Column(name = "total_cost")
    private Double totalCost;

    /**
     * 港币价
     */
    @Column(name = "hkd")
    private Double hkd;

    /**
     * 销售拟定单价
     */
    @Column(name = "asp")
    private Double asp;

    /**
     * 总价
     */
    @Column(name = "count_price")
    private Double countPrice;

    /**
     * 单个利润
     */
    @Column(name = "unit_profit")
    private Double unitProfit;

    /**
     * 总利润
     */
    @Column(name = "count_profit")
    private Double countProfit;

    /**
     * 盈利百分比
     */
    @Column(name = "gpm")
    private Double gpm;

    /**
     * 顾客
     */
    @Column(name = "client_name")
    private String clientName;

    /**
     * 销售人
     */
    @Column(name = "salesman")
    private String salesman;

    @Column(name = "sale_date")
    private Date saleDate;

    @Column(name = "disabled")
    private Integer disabled;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    private String skuId;


    private static final long serialVersionUID = 1L;
}