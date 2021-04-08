package com.kintexgroup.hkpsi.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author lmao             pengli
 * @since 2020/8/26 16:54   2020/10/09
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StageVO {

    //private List<Map<String, Object>> vendorSkus;

    /**
     * 投标编号
     */
    private String bidId;

    /**
     * imei唯一标识码
     */
    private String imei;

    /**
     * 描述
     */
    private String text;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 型号
     */
    private String model;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 运营商
     */
    private String carrierName;

    /**
     * 供应商外观成色
     */
    private String grade;
}
