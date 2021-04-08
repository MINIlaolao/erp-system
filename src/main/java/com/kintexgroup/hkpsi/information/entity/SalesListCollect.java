package com.kintexgroup.hkpsi.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 清单汇总
 *
 * @author LMAO
 * @since 2020/11/17 14:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hkpsi_sales_list_collect")
public class SalesListCollect implements Serializable {
    /**
     * 销售汇总id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 销售报表id
     */
    @Column(name = "sale_order_id")
    private String saleOrderId;

    /**
     * 商品总数量
     */
    @Column(name = "count_quantity")
    private Integer countQuantity;

    /**
     * 美元总价
     */
    @Column(name = "totalUsd")
    private Double totalUsd;

    /**
     * 汇率
     */
    @Column(name = "exchange_rate")
    private Double exchangeRate;

    /**
     * 港元总价
     */
    @Column(name = "total_hkd")
    private Double totalHkd;

    /**
     * 运费
     */
    @Column(name = "freight")
    private Double freight;

    /**
     * 总成本价
     */
    @Column(name = "total_cost")
    private Double totalCost;

    /**
     * 总销售额
     */
    @Column(name = "total_sales")
    private Double totalSales;

    /**
     * 盈利
     */
    @Column(name = "margin")
    private Double margin;

    /**
     * 盈利百分比
     */
    @Column(name = "gpm")
    private Double gpm;

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

    private static final long serialVersionUID = 1L;
}