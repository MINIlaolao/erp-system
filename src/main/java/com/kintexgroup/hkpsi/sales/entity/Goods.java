package com.kintexgroup.hkpsi.sales.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 设备表
 *
 * @author lmao
 * @since 2020/9/27 16:08
 */
@Data
@Table(name = "hkpsi_goods")
public class Goods implements Serializable {

    /**
     * 手机编号
     */
    @Id
    @Column(name = "device_id")
    @GeneratedValue(generator = "JDBC")
    private Integer deviceId;

    /**
     * 订单编号
     */
    @Column(name = "sale_order")
    private String saleOrder;

    /**
     * 中标号
     */
    @Column(name = "won_bid_number")
    private Long wonBidNumber;

    /**
     * 我们的sku
     */
    @Column(name = "sku_id")
    private String skuId;

    /**
     * 商品单价
     */
    @Column(name = "price")
    private Double price;

    /**
     * 电话设备识别码1
     */
    @Column(name = "imei1")
    private String imei1;

    /**
     * 电话设备识别码2
     */
    @Column(name = "imei2")
    private String imei2;

    /**
     * SIM锁
     */
    @Column(name = "locked")
    private Integer locked;

    /**
     * iCloud锁
     */
    @Column(name = "icloud")
    private Integer icloud;

    /**
     * 设备入库时间
     */
    @Column(name = "device_in_time")
    private String deviceInTime;

    /**
     * 设备出库时间
     */
    @Column(name = "device_out_time")
    private String deviceOutTime;

    @Column(name = "disabled")
    private Integer disabled;

    @Column(name = "grade")
    private String grade;

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

    /**
     * 类型
     */
    private String category;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 型号名称
     */
    private String modelName;

    private Object attribute;

    /**
     * 型号号码
     */
    private String model;

    /**
     * 备注
     */
    private String remark;

    /**
     * 内存
     */
    private Integer capacity;

    private List<String> imeis;

    private static final long serialVersionUID = 1L;
}