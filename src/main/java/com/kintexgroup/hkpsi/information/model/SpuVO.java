package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.Map;

/**
 * @author pengli
 * @since 2020/9/8 2:28 下午
 */
@Data
public class SpuVO {

    private Integer id;

    private String name;

    /**
     * 种类
     */
    private String category;

    /**
     * 描述
     */
    private Map<String, Object> spec;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 是否可用
     */
    private Integer disabled;

}
