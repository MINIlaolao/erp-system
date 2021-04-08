package com.kintexgroup.hkpsi.inventory.entity;

import javax.persistence.*;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "hkpsi_inventory_goods")
public class InventoryGoodsEntity extends BaseEntity {
    /**
     * id
     */
    @Id
    @Column(name = "record_id")
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * LotID
     */
    @Column(name = "lot_id")
    private String lotId;

    /**
     * 品牌
     */
    @Column(name = "brand")
    private String brand;

    /**
     * 模型
     */
    @Column(name = "model")
    private String model;

    /**
     * 型号名称
     */
    @Column(name = "model_name")
    private String modelName;

    /**
     * 型号名称
     */
    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "imei")
    private String imei;

    @Column(name = "imei2")
    private String imei2;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "color")
    private String color;

    /**
     * 品牌
     */
    @Column(name = "source_oem")
    private String sourceOEM;

    /**
     * Part No.
     */
    @Column(name = "source_part_no")
    private String sourcePartNo;

    /**
     * model
     */
    @Column(name = "source_model")
    private String sourceModel;

    /**
     * 容量文本
     */
    @Column(name = "source_capacity")
    private String sourceCapacity;

    /**
     * 运营商
     */
    @Column(name = "source_carrier")
    private String sourceCarrier;

    /**
     * 颜色
     */
    @Column(name = "source_color")
    private String sourceColor;

    /**
     * 等级
     */
    @Column(name = "source_grade")
    private String sourceGrade;

    /**
     * IMEI No.
     */
    @Column(name = "source_code")
    private String sourceCode;

    /**
     * IMEI 2
     */
    @Column(name = "source_imei2")
    private String sourceIMEI2;

    /**
     * 价格
     */
    @Column(name = "price")
    private Double price;

    /**
     * 港币
     */
    @Column(name = "hkd")
    private Double hkd;

    /**
     * 包装箱id
     */
    @Column(name = "carton_id")
    private String cartonId;

    /**
     * 信息页
     */
    @Column(name = "url")
    private String url;
}