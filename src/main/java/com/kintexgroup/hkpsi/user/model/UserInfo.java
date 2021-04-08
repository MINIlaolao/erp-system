package com.kintexgroup.hkpsi.user.model;

import lombok.Data;

import java.util.List;

/**
 * @author junhangs
 * @author 2021-03-17 15:53
 **/
@Data
public class UserInfo {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 工号
     */
    private String employeeNo;

    /**
     * 名字
     */
    private String employeeName;

    /**
     * 角色
     */
    private List<Integer> access;
}
