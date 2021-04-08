package com.kintexgroup.hkpsi.information.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author junhangs
 * @since 2020/9/11 11:57
 */
@Builder
@Data
public class VendorSkuDTO {
    /**
     * 供应商 ID
     */
    private Integer vendor;
    /**
     * 描述
     */
    private String description;
    /**
     * 等级
     */
    private String grade;
    /**
     * description|grade
     */
    private String tag;
    /**
     * kintex sku id
     */
    private String kSku;
    /**
     * 禁用
     */
    private Integer disabled;
}
