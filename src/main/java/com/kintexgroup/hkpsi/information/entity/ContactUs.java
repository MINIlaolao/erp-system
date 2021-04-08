package com.kintexgroup.hkpsi.information.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 联系我们
 *
 * @author lmao
 * @since 2020/9/14 14:20
 */
@Data
@Table(name = "website_wireless_contact_us")
public class ContactUs implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;


    private static final long serialVersionUID = 1L;
}