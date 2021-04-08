package com.kintexgroup.hkpsi.information.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author junhangs
 * @JsonInclude(JsonInclude.Include.NON_NULL)：实体类与json互转的时候 属性值为null的不参与序列化
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarrierVO implements Serializable {

    private Integer id;
    private String name;
    private String region;
    private Boolean disabled;
    private Boolean editable;
}
