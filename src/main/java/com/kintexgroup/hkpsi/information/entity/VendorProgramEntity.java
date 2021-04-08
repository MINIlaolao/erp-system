package com.kintexgroup.hkpsi.information.entity;

import javax.persistence.*;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hkpsi_vendor_program")
public class VendorProgramEntity extends BaseEntity {
    /**
     * 项目id
     */
    @Id
    @Column(name = "program_id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 项目名称
     */
    @Column(name = "program_name")
    private String name;

    /**
     * 所属的供应商
     */
    @Column(name = "program_vendor")
    private Integer vendor;

    @Column(name = "disabled")
    private Integer disabled;
}