package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.Category;
import com.kintexgroup.hkpsi.information.model.CategoryVO;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmao
 * @since 2020/9/8 14:54
 */
@Resource
public interface CategoryDao {

    /**
     * 插入一条类别记录
     *
     * @param record 实体类
     * @return 成功 1:失败:0
     */
    boolean insertOne(Category record);

    /**
     * 更新一条类别记录
     *
     * @param record 实体类
     * @return 成功 1:失败:0
     */
    boolean updateById(Category record);

    /**
     * 删除一条记录
     *
     * @param id 分类id
     * @return 成功 1:失败:0
     */
    boolean deleteOne(Integer id);

    /**
     * 检查一条记录
     *
     * @param name 分类名称
     * @return 成功 1:失败:0
     */
    boolean check(@Param("name") String name);

    /**
     * 检查这条是否存在
     *
     * @param id   分类id
     * @param name 分类名称
     * @return 成功 1:失败:0
     */
    boolean checkIsExist(@Param("id") Integer id, @Param("name") String name);

    /**
     * 查询一条记录
     *
     * @param id 分类id
     * @return 结果
     */
    CategoryVO selectOneById(Integer id);

    /**
     * 查询所有记录
     *
     * @return 结果集list
     */
    List<CategoryVO> selectMany();

    /**
     * 根据分类id  联合spu表查询分类名
     *
     * @param id 分类id
     * @return 返回查数据出来的条数
     */
    Integer selectByCategory(@Param("id") int id);
}
