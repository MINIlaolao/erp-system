package com.kintexgroup.hkpsi.information.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.model.ClientPageDTO;
import com.kintexgroup.hkpsi.information.entity.ClientEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author pengli
 * @since 2020/9/10 1:55 下午
 */
@Repository
public interface ClientDao extends Mapper<ClientEntity> {

    /**
     * 添加一条顾客信息
     *
     * @param record Client
     * @return 成功true 失败false
     */
//    boolean insert(ClientEntity record);

    /**
     * 查询一条顾客信息
     *
     * @param id id
     * @return 成功true 失败false
     */
    ClientEntity selectOneById(Integer id);

    /**
     * 根据客户ID查询客户姓名
     *
     * @param id id
     * @return 查到的名字
     */
//    String selectNameById(Integer id);

    /**
     * 查询所有顾客信息
     *
     * @param dto
     * @return 结果集list
     */
    Page<ClientEntity> selectBatch(ClientPageDTO dto);

    /**
     * 更新一条顾客信息
     *
     * @param record Client
     * @return 成功true 失败false
     */
//    boolean update(ClientEntity record);

    /**
     * 删除一条顾客信息
     *
     * @param id id
     * @return 成功true 失败false
     */
    boolean deleteById(@Param("id") Integer id);

    /**
     * 检查数据库是否存在这条记录
     *
     * @param name 名字
     * @return 查询成功 : true | 查询失败 : false
     */
    boolean check(@Param("name") String name);

}
