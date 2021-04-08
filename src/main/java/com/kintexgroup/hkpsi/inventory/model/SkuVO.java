package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/9/15 8:26 下午
 */
@Data
public class SkuVO {

    private Integer id;

    private Integer spuId;

    /**
     * 属性
     */

    private Object attribute;

    /**
     * 库存数
     */
    private Integer quantity;

    /**
     * 是否禁用
     */
    private Integer disabled;
}
