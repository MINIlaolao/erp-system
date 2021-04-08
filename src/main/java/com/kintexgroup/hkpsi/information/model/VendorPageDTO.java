package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/24 4:50 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VendorPageDTO extends BasePageDTO {
    /**
     * 供应商代号
     */
    private String code;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 是否可用
     */
    private Integer disabled;
}
