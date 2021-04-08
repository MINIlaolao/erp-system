package com.kintexgroup.hkpsi.user.model;

import lombok.Data;

import java.util.List;

/**
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
@Data
public class UserDTO {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 密码
     */
    private String password;

    /**
     * 员工编号
     */
    private String employeeNo;

    /**
     * 员工姓名
     */
    private String employeeName;

    /**
     * 员工邮箱
     */
    private String employeeEmail;

    /**
     * 所属公司名称
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
     * 名字
     */
    private String name;

    /**
     * 创建人 id
     */
    private Integer createdBy;

    /**
     * 更新人 id
     */
    private Integer updatedBy;

}
