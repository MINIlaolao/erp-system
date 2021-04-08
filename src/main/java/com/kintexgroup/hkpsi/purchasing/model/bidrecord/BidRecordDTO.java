package com.kintexgroup.hkpsi.purchasing.model.bidrecord;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author pengli
 * @since 2020/9/2 15:57
 */
@Data
public class BidRecordDTO {
    
    /**
     * 清单id
     */
    private String id;

    /**
     * 投标编号
     */
    private String bidId;

    /**
     * 投标数量
     */
    private Integer bidQty;

    /**
     * 投标单价
     */
    private Double bidPrice;

    /**
     * 投标港币
     */
    private Double bidHkd;

    /**
     * 往期中标价
     */
    private Double bidHigh;

    /**
     * 中标id
     */
    private String wonId;

    /**
     * 中标数量
     */
    private Integer wonQty;

    /**
     * 中标单价
     */
    private Double wonPrice;

    /**
     * 中标港币
     */
    private Double wonHkd;
    
    private String tag;

    private String warehouse;

    private String sku;
    
    private String brand;

    private String description;

    private String modelNumber;

    private String modelName;

    private String model;
    
    private String capacity;
    
    private String carrier;
    
    private String color;
    
    private String condition;
    
    private String grade;

    @JsonProperty(value = "FMiPLocked")
    private String FMiPLocked;

    @JsonProperty(value = "SIMLocked")
    private String SIMLocked;
    
    @JsonProperty(value = "LCDHealth")
    private String LCDHealth;
}
