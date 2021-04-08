package com.kintexgroup.hkpsi.information.model;

import lombok.Data;


/**
 * @author pengli
 * @since 2020/9/15 7:02 下午
 */

@Data
public class SkuAttributeVO {

    private Integer id;

    /**
     * 种类id
     */
    private Integer categoryId;

    /**
     * 属性名（英文名）
     */
    private String name;

    /**
     * 属性中文名
     */
    private String description;


}
