package com.kintexgroup.hkpsi.user.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.user.entity.UserEntity;
import com.kintexgroup.hkpsi.user.model.AccessVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息Dao
 *
 * @author gradylo
 * @since 2020/9/2 10:14 上午
 */
@Repository
public interface UserDao {
    
    /**
     * 根据 id 查询用户信息
     */
    UserEntity selectOneById(Integer userId);

    /**
     * 根据 employeeNo 查询用户信息
     */
    UserEntity selectOneByEmployeeNo(String employeeNo);

    /**
     * 根据 employeeEmail 查询用户信息
     */
    UserEntity selectOneByEmployeeEmail(String employeeEmail);

    /**
     * 添加用户
     *
     * @param dto
     * @return 判断是否添加成功
     */
    boolean insertOne(UserEntity dto);

    /**
     * 检查数据库是否存在这条记录
     *
     * @param account
     * @return 查询成功 : true | 查询失败 : false
     */
    boolean check(@Param("account") String account);

    /**
     * 修改用户
     *
     * @param dto
     * @return 判断是否添加成功
     */
    boolean updateOneById(UserEntity dto);

    /**
     * 获取所有用户列表
     *
     * @return
     */
    List<UserEntity> selectAll();

    /**
     * 分页查询用户列表
     *
     * @return
     */
    Page<UserEntity> selectPage();

    /**
     * 删除一个用户信息
     *
     * @param id 用户id
     * @return
     */
    boolean deleteOne(int id);

    /**
     * 查询所有权限列表
     *
     * @return
     */
    List<AccessVO> getAllAccess();

}
