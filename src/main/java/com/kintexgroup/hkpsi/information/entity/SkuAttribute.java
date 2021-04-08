package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pengli
 * @since 2020/9/15 6:45 下午
 */

@Data
public class SkuAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 种类id
     */
    private Integer categoryId;

    /**
     * 属性名
     */
    private String name;

    /**
     * 这个属性是干嘛用的
     */
    private String description;

}
