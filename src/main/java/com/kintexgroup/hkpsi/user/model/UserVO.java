package com.kintexgroup.hkpsi.user.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author gradylo
 * @since 2020/9/23 10:14 上午
 */
@Data
public class UserVO {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 员工编号
     */
    private String employeeNo;

    /**
     * 员工名称
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
