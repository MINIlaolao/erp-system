package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;

/**
 * @author lmao
 * @since 2020/9/30 10:14
 */
@Data
public class InventoryDTO {

    private String skuId;

    /**
     * 库存数
     */
    private Integer inventoryQuantity;
}
