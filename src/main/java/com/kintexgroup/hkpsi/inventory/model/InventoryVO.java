package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;

/**
 * @author lmao                  pengli
 * @since 2020/10/13 16:48       2020/11/20
 */
@Data
public class InventoryVO {

    /**
     * 供应商skuId
     */
    private String vendorSkuId;

    /**
     * skuId
     */
    private String skuId;

    /**
     * 库存数
     */
    private Integer inventoryQuantity;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private String capacity;

    /**
     * 型号
     */
    private String model;

    /**
     * 运营商
     */
    private String carrier;

    /**
     * 库存金额
     */
    private Double countPrice;

}


