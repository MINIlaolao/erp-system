package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

import java.util.Map;

/**
 * @author pengli
 * @since 2020/11/24 4:31 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpuPageDTO extends BasePageDTO {
    private String name;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 种类
     */
    private String category;

    /**
     * 描述
     */
    private Map<String, Object> spec;

    /**
     * 是否可用
     */
    private Integer disabled;

}
