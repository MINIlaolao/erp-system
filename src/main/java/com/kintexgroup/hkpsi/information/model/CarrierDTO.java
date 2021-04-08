package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author junhangs
 */
@Data
public class CarrierDTO implements Serializable {

    /**
     * 运营商名称
     */
    private String name;

    /**
     * 运营商所属地区
     */
    private String region;


    /**
     * 是否禁用
     */
    private Boolean disabled;


    private static final long serialVersionUID = 1L;

}
