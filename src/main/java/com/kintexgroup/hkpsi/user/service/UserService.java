package com.kintexgroup.hkpsi.user.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;
import com.kintexgroup.hkpsi.user.model.AccessVO;
import com.kintexgroup.hkpsi.user.model.UserDTO;
import com.kintexgroup.hkpsi.user.model.UserVO;

import java.util.List;

/**
 * @author gradylo
 * @since 2020/9/23 10:31 上午
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param dto 创建的用户
     * @return 一条记录
     */
    boolean addOne(UserDTO dto);

    /**
     * 根据ID更新一条用户信息
     *
     * @param dto 修改的用户
     * @return 成功 true/失败 false
     */
    boolean update(UserDTO dto);

    /**
     * 获取所有用户列表
     */
    List<UserVO> selectAll();

    /**
     * 分页查询用户
     *
     * @param dto 页码
     * @return 结果集
     */
    PageInfo<UserVO> selectPage(BasePageDTO dto);

    /**
     * 获取用户信息
     *
     * @return 结果集
     */
    UserVO selectOne();

    /**
     * 删除一个用户信息
     *
     * @param id 用户id
     * @return 是否成功
     */
    boolean deleteOne(int id);


    /**
     * 查询所有权限列表
     *
     * @return 结果集
     */
    List<AccessVO> getAllAccess();

}
