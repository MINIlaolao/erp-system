package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/9/15 7:01 下午
 */
@Data
public class SkuAttributeDTO {

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


    /**
     * 修改的时候才会用
     */
    private Boolean isDeleted;

}
