package com.kintexgroup.hkpsi.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LMAO
 * @since 2020/11/13 14:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stage implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 中标编号
     */
    @Column(name = "won_id")
    private String wonId;


    /**
     * 唯一标识码
     */
    @Column(name = "imei")
    private String imei;

    /**
     * 供应商SKU
     */
    @Column(name = "vendor_sku")
    private String vendorSku;

    /**
     * 用于扫码
     */
    @Column(name = "tag")
    private String tag;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    private static final long serialVersionUID = 1L;

    /**
     * 投标编号
     */
    private String bidId;

    /**
     * 描述
     */
    private String text;

    /**
     * 供应商成色
     */
    private String vendorGrade;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 型号号码
     */
    private String model;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

}