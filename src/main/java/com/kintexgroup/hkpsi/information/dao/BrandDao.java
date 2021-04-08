package com.kintexgroup.hkpsi.information.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.entity.Brand;
import com.kintexgroup.hkpsi.information.model.BrandPageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengli
 * @since 2020/9/8 6:40 下午
 */
@Repository
public interface BrandDao {

    /**
     * 插入一条品牌记录
     *
     * @param entity 实体类
     * @return 成功 : true | 查询失败 : false
     */
    boolean insertOne(Brand entity);


    /**
     * 更新一条spuBrand信息
     *
     * @param entity 实体类
     * @return 成功 : true | 查询失败 : false
     */
    boolean updateOne(Brand entity);

    /**
     * 根据品牌唯一标识移除一条品牌记录
     *
     * @param id        品牌唯一标识
     * @param removedBy 移除该条基础的用
     * @return 成功 : true | 查询失败 : false
     */
    boolean removeOne(@Param("id") int id, @Param("removedBy") int removedBy);

    /**
     * 根据品牌唯一标识查询一条品牌记录
     *
     * @param id     品牌唯一标识
     * @param isCore 是否只获取核心字段
     * @return 最小数据的品牌实体类
     */
    Brand selectOne(@Param("id") int id, @Param("isCore") boolean isCore);

    /**
     * 根据品牌名字或者状态查询信息
     *
     * @param dto 传进来的dto
     * @return 查询到的brand结果集
     */
    Page<Brand> selectOneOrMany(BrandPageDTO dto);

    /**
     * 根据品牌id  联合brand表和spu表查询品牌名
     *
     * @param id 品牌id
     * @return 返回查数据出来的条数
     */
    Integer selectByName(@Param("id") int id);

    /**
     * 根据品牌唯一标识查询一条品牌记录
     *
     * @param name   品牌唯一标识
     * @param isCore 是否只获取核心字段
     * @return 最小数据的品牌实体类
     */
    Brand selectOneBrand(@Param("name") String name, @Param("isCore") boolean isCore);

    /**
     * 查询所有spuBrand信息
     *
     * @param isCore 判断是否需要查询创建、更新、删除的操作人以及时间
     * @return 返回查询的结果集合
     */
    List<Brand> selectAll(@Param("isCore") boolean isCore);

    /**
     * 检查表中是否存在该名字，更新时需要传入id
     *
     * @param name 品牌名称
     * @param id   品牌唯一标识
     * @return 成功 : true | 失败 : false
     */
    boolean nameIsAlreadyExists(@Param("name") String name, @Param("id") int id);
}
