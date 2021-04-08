package com.kintexgroup.hkpsi.information.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.StringJoiner;

/**
 * @author lmao
 */
@Data
public class VendorTags {

    @JsonProperty("modelName")
    private String modelName;

    @JsonProperty("carrier")
    private String carrier;

    @JsonProperty("condition")
    private String condition;

    @JsonProperty("color")
    private String color;

    @JsonProperty("grade")
    private String grade;

    @JsonProperty("description")
    private String description;

    @JsonProperty("modelNumber")
    private String modelNumber;

    @JsonProperty("model")
    private String model;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("fmip")
    private String fmip;

    @JsonProperty("capacity")
    private String capacity;

    @Override
    public String toString() {
        return new StringJoiner("|")
            .add(modelName)
            .add(carrier)
            .add(condition)
            .add(color)
            .add(grade)
            .add(description)
            .add(modelNumber)
            .add(model)
            .add(sku)
            .add(brand)
            .add(fmip)
            .add(capacity)
            .toString();
    }
}