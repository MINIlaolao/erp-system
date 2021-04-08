package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author lmao
 */
@Data
public class VendorDTO {

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
