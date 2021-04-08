package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author junhangs
 */
@Data
public class Carrier implements Serializable {

    /**
     * 运营商编号
     */
    private Integer id;

    /**
     * 运营商姓名
     */
    private String name;

    /**
     * 运营商地区
     */
    private String region;

    /**
     * 可编辑
     */
    private Boolean editable;

    /**
     * 是否禁用
     */
    private Boolean disabled;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人 id
     */
    private Integer createdBy;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人 id
     */
    private Integer updatedBy;

    private static final long serialVersionUID = 1L;
}
