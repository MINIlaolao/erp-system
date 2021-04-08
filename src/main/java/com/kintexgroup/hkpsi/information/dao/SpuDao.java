package com.kintexgroup.hkpsi.information.dao;


import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.entity.Spu;
import com.kintexgroup.hkpsi.information.model.SpuPageDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengli
 * @since 2020/9/8 2:25 下午
 */
@Repository
public interface SpuDao {

    /**
     * 通过查询条件，查询一条或多条SPU信息
     *
     * @param dto dto
     * @return 查询出来的spu的信息
     */
    Page<Spu> selectOneOrMany(SpuPageDTO dto);

    /**
     * 通过ID查询一条SPU信息
     *
     * @param id id
     * @return 查询出来的spu的一条信息
     */
    Spu selectOne(Integer id);

    /**
     * 查询所有SPU信息
     *
     * @return 查询出来的所有spu集合
     */
    List<Spu> selectAll();

    /**
     * 添加一条SPU信息
     *
     * @param record Spu
     * @return 成功：true / 失败：false
     */
    boolean insert(Spu record);

    /**
     * 根据id删除一条SPU信息
     *
     * @param id spu_id
     * @return 成功：true / 失败：false
     */
    boolean deleteOne(Integer id);

    /**
     * 根据ID更新一条SPU信息
     *
     * @param record Spu
     * @return 成功：true / 失败：false
     */
    boolean updateOne(Spu record);

    /**
     * 检查数据库是否存在这条记录
     *
     * @param name  名字
     * @param brand 品牌
     * @return 查询成功 : true | 查询失败 : false
     */
    int check(@Param("name") String name, @Param("brand") String brand);


    /**
     * 批量插入 spu
     *
     * @param spuList spuList
     * @return isSuccess
     */
    boolean batchInsertSpu(@Param("spuList") Spu[] spuList);

}
