package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.List;

/**
 * @author lmao
 * @since 2020/9/8 14:58
 */
@Data
public class CategoryDTO {

    private Integer id;
    private String name;
    private Integer disabled;
    private List<SkuAttributeDTO> attributes;
}
