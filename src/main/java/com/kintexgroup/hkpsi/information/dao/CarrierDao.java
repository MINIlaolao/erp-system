package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.Carrier;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmao
 * @author PENGLI
 */
@Repository
public interface CarrierDao {

    /**
     * 根据ID删除一条运营商信息
     *
     * @param id 运营商id
     * @return 删除是否成功
     */
    boolean deleteOne(Integer id);

    /**
     * 如果不存在，则插入一条运营商信息
     *
     * @param record 实体类
     * @return 插入是否成功
     */
    boolean insertOne(Carrier record);

    /**
     * 添加时检查 这条信息是否已存在
     *
     * @param name   运营商姓名
     * @param region 运营商地区
     * @return false : 查询到重复的值 || true  : 没有查询到重复的值
     */
    boolean check(@Param("name") String name, @Param("region") String region);

    /**
     * 根据id查询一条运营商信息
     *
     * @param id 运营商id
     * @return 返回实体类
     */
    Carrier selectOne(Integer id);

    /**
     * 查询所有的运营商信息
     *
     * @return 返回实体类集合
     */
    List<Carrier> selectMany();

    /**
     * 根据ID更新一条运营商信息
     *
     * @param record 实体类
     * @return 更新是否成功
     */
    boolean updateOne(Carrier record);
}
