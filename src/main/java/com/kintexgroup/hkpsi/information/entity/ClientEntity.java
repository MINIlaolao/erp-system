package com.kintexgroup.hkpsi.information.entity;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pengli
 * @since 2020/9/10 1:54 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hkpsi_client")
public class ClientEntity extends BaseEntity {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    
    /**
     * 顾客名称
     */
    @Column(name = "client_name")
    private String name;

    /**
     * 联系方式
     */
    @Column(name = "client_contact")
    private String contact;
    
    /**
     * 是否可用
     */
    @Column(name = "disabled")
    private Integer disabled;
}
