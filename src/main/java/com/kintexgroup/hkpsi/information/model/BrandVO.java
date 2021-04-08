package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/9/8 6:25 下午
 */
@Data
public class BrandVO {

    /**
     * 品牌id
     */
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 是否可用
     */
    private Integer disabled;

}
