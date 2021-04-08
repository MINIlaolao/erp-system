package com.kintexgroup.hkpsi.inventory.entity;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 装箱信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hkpsi_carton")
public class CartonEntity extends BaseEntity {
    /**
     * id
     */
    @Id
    @Column(name = "carton_id")
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 箱号
     */
    @Column(name = "carton_number")
    private Integer cartonNumber;

    /**
     * 箱内物品汇总信息
     */
    @Column(name = "content")
    private String content;
}