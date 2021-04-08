package com.kintexgroup.hkpsi.information.dao;

import com.kintexgroup.hkpsi.information.entity.SkuAttribute;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author pengli
 * @since 2020/9/15 6:50 下午
 */

@Repository
public interface SkuAttributeDao {

    /**
     * 通过categoryId查询一条SkuAttribute信息
     *
     * @param id id
     * @return 查询的结果集
     */
    List<SkuAttribute> selectOne(Integer id);

    /**
     * 查询所有SkuAttribute信息
     *
     * @return 查询的结果集
     */
    List<SkuAttribute> selectAll();

    /**
     * 添加一条SkuAttribute信息
     *
     * @param list list
     * @return 成功 ：true / 失败 ：false
     */
    boolean insert(List<SkuAttribute> list);

    /**
     * 根据id删除一条SkuAttribute信息
     *
     * @param ids categoryIds
     * @return 成功 ：true / 失败 ：false
     */
    boolean delete(@Param("list") List<Integer> ids);

    /**
     * 根据ID更新一条SkuAttribute信息
     *
     * @param categoryId 种类id
     * @param record     record
     * @return 成功 ：true / 失败 ：false
     */
    boolean updateOne(@Param("categoryId") Integer categoryId,
                      @Param("list") List<SkuAttribute> record);

    /**
     * 检查数据是否存在
     *
     * @param categoryId 种类id
     * @param name       种类名称
     * @return 成功 ：true / 失败 ：false
     */
    boolean check(@Param("categoryId") Integer categoryId, @Param("name") String name);

    /**
     * 检查数据表中是否有这条数据
     *
     * @param id 属性id
     * @return 成功 ：true / 失败 ：false
     */
    boolean checkUpdate(@Param("id") Integer id);

}
