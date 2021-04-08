package com.kintexgroup.hkpsi.information.model.vendorsku;

import lombok.Data;

/**
 * @author junhangs
 * @since 2021/1/16 16:19
 */
@Data
public class VendorSkuBase {
    
    /**
     * vendor sku id
     * 更新删除时有用
     */
    private String id;
    
    /**
     * description
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
    private Integer vendorId;
    
    /**
     * 对应的 kintex sku id
     */
    private String kSkuId;

    /**
     * 禁用
     */
    private Integer disabled;
}

