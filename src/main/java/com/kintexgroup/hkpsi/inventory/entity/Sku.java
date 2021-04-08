package com.kintexgroup.hkpsi.inventory.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author lmao
 * @since 2020/9/11 13:58
 */
@Data
public class Sku {

    /**
     * sku编号
     */
    private Integer id;

    /**
     * spuId
     */
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


    /**
     * 创建时间
     */

    private Date createdAt;

    /**
     * 创建者ID
     */
    private Integer createdBy;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 更新者ID
     */
    private Integer updatedBy;
}