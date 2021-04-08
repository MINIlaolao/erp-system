package com.kintexgroup.hkpsi.inventory.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lmao
 * @since 2020/9/27 17:56
 */
@Data
public class Inventory implements Serializable {


    @Id
    @Column(name = "sku_id")
    private String skuId;

    /**
     * 库存数
     */
    @Column(name = "inventory_quantity")
    private Integer inventoryQuantity;

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
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

}