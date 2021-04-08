package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author lmao
 * @since 2020/10/12 18:04
 */
@Data
public class VendorSkuVO {


    /**
     * 供应商 SKU 的 description
     */
    private String text;

    private String vGrade;

    private String tag;

    /**
     * 供应商的name
     */
    private String vendorName;

    /**
     * Kintex SKU Id
     */
    private String sku;

    private Integer disabled;


    private String color;
    private String model;
    private String grade;
    private String carrier;
    private Integer capacity;

}


