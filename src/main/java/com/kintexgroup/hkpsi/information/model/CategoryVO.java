package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @since 2020/9/8 14:59
 */
@Data
public class CategoryVO {

    private Integer id;
    private String name;
    private Integer disabled;
    private List<Map<String, Object>> attributes;
}
