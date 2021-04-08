package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

/**
 * @author PENGLI
 */
@Data
public class VendorVO {

    /**
     * 供应商 id 内部使用
     */
    private Integer id;

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
