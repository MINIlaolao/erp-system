package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author pengli
 * @since 2020/9/8 6:24 下午
 */
@Data
public class BrandDTO {


    /**
     * 品牌名称
     */
    @NotBlank
    private String name;

    /**
     * 是否可用
     */
    @NotBlank
    private Integer disabled;

}
