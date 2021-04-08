package com.kintexgroup.hkpsi.information.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author LMAO
 * @since 2020/11/18 18:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesListVO {
    /**
     * 销售报表id
     */
    private String saleOrderId;

    /**
     * 供应商仓库
     */
    private String warehouse;

    /**
     * 供应商产品编号
     */
    private String item;

    /**
     * 产品标号
     */
    private String description;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 型号号码
     */
    private String modelNumber;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 颜色
     */
    private String color;

    /**
     * 运营商
     */
    private String carrier;

    /**
     * 云端存储
     */
    private Integer icloud;

    /**
     * SIM卡状态
     */
    private Integer simStatus;

    /**
     * 我们的商品等级
     */
    private String grade;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 单个成本价
     */
    private Double costPrice;

    /**
     * 总成本价
     */
    private Double totalCost;

    /**
     * 港币价
     */
    private Double hkd;

    /**
     * 销售拟定单价
     */
    private Double asp;

    /**
     * 总价
     */
    private Double countPrice;

    /**
     * 单个利润
     */
    private Double unitProfit;

    /**
     * 总利润
     */
    private Double countProfit;

    /**
     * 盈利百分比
     */
    private Double gpm;

    /**
     * 顾客
     */
    private String clientName;

    /**
     * 销售人
     */
    private String salesman;

    private Date saleDate;

    private Integer disabled;
}


