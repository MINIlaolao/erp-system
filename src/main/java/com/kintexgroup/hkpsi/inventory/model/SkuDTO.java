package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;


/**
 * @author lmao
 * @since 2020/9/11 14:07
 */
@Data
public class SkuDTO {

    private Integer spuId;

    /**
     * 属性
     */

    private Object attribute;

    /**
     * 是否禁用
     */
    private Integer disabled;

    /**
     * 库存数
     */
    private Integer quantity;
}
