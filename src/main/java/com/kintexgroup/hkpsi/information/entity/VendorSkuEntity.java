package com.kintexgroup.hkpsi.information.entity;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author junhangs
 * @since 2020/9/11 11:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VendorSkuEntity extends BaseEntity {
    /**
     * vendor sku id
     * 更新删除时有用
     */
    private String id;

    /**
     * 供应商 SKU 的 description
     */
    private String description;
    
    /**
     * grade
     */
    private String grade;

    /**
     * tag description|grade
     */
    private String tag;

    /**
     * vendor id
     */
    private Integer vendor;

    /**
     * 对应的 kintex sku id
     */
    private String kSku;

    /**
     * 禁用
     */
    private Integer disabled;
}