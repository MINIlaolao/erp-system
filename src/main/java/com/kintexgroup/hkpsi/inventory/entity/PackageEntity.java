package com.kintexgroup.hkpsi.inventory.entity;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author LMAO
 * @since 2021/1/25 11:41
 * 包装信息表
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hkpsi_package")
public class PackageEntity extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 包装生成的编码
     */
    @Column(name = "package_no")
    private String packageNo;

    /**
     * 包装内信息json格式
     */
    @Column(name = "content")
    private String content;
}