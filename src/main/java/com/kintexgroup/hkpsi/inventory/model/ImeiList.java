package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author LMAO
 * @since 2020/11/13 14:55
 */
@Data
public class ImeiList {

    @NotNull
    private String wonId;
    private String brand;
    private String capacity;
    private String carrier;
    private String grade;
    private String color;
    @NotNull
    private String description;
    @NotNull
    private String imei;
    private String model;
    private String modelName;

    private String skuId;
}


