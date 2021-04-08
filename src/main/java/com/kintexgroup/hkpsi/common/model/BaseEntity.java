package com.kintexgroup.hkpsi.common.model;

import lombok.Data;

import javax.persistence.Column;

@Data
public abstract class BaseEntity {

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private String createdAt;
    
    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private String updatedAt;
    
    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private Integer updatedBy;
}
