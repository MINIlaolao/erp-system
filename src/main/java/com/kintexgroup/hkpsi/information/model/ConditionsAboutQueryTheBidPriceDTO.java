package com.kintexgroup.hkpsi.information.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kintexgroup.hkpsi.common.util.ClassPropertyUtil;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LMAO
 * @since 2021/3/16 14:45
 * Condition of extract the bid price
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConditionsAboutQueryTheBidPriceDTO {

    /**
     * k:name
     * {
     * warehouse,sku,oem,description,modelNumber,modelName,model,
     * capacity,carrier,color,condition,grade,fimpLocked,simLocked,ldcHealth
     * }
     * v:value
     */

    private String warehouse;
    private String sku;
    private String oem;
    private String description;
    private String modelNumber;
    private String modelName;
    private String model;
    private String capacity;
    private String carrier;
    private String color;
    private String condition;
    private String grade;
    private String fmipLocked;
    private String simLocked;
    private String lcdHealth;

    public boolean customEquals(ConditionsAboutQueryTheBidPriceDTO targetTag) {
        var list = ClassPropertyUtil.getFieldsInfo(targetTag)
            .stream().filter(x -> x.get("value") != null).collect(Collectors.toList());
        for (Map<String, Object> property : list) {
            //todo 没看懂
            var value = property.get("value");
            var name = property.get("name").toString();
            if (!value.equals(ClassPropertyUtil.getFieldValueByName(name, this))) {
                return false;
            }
        }
        return true;
    }
    
}


