package com.kintexgroup.hkpsi.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gradylo
 * @since 2020/9/23 4:43 下午
 */
@Data
public class UserEntity implements Serializable {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 密码
     */
    private String password;

    /**
     * 名字
     */
    private String employeeNo;

    /**
     * 名字
     */
    private String employeeName;

    /**
     * 名字
     */
    private String employeeEmail;

    /**
     * 所属公司名字
     */
    private String companyName;

    /**
     * 角色
     */
    private List<Integer> access;

    /**
     * 是否禁用，0：不禁用，1：禁用
     */
    private Integer disabled;

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

}
