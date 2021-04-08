package com.kintexgroup.hkpsi.inventory.model;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImeiRecordVO extends BaseEntity {
    /**
    * id
    */
    private String id;

    /**
    * LotID
    */
    private String lotId;

    /**
    * 品牌
    */
    private String brand;

    /**
    * 模型
    */
    private String model;

    /**
    * 型号名称
    */
    private String modelName;

    /**
    * 型号名称
    */
    private String modelNumber;

    /**
     * IMEI
     */
    private String imei;

    /**
     * IMEI
     */
    private String imei2;

    /**
     * 序列号
     */
    private String serialNumber;

    /**
     * 容量
     */
    private Short capacity;

    /**
     * 颜色
     */
    private String color;

    /**
    * 品牌
    */
    private String sourceOem;

    /**
    * Part No.
    */
    private String sourcePartNo;

    /**
    * model
    */
    private String sourceModel;

    /**
    * 容量文本
    */
    private String sourceCapacity;

    /**
    * 运营商
    */
    private String sourceCarrier;

    /**
    * 颜色
    */
    private String sourceColor;

    /**
    * 等级
    */
    private String sourceGrade;

    /**
    * IMEI No.
    */
    private String sourceCode;

    /**
    * IMEI 2
    */
    private String sourceImei2;

    /**
     * 价格
     */
    private Double price;
}