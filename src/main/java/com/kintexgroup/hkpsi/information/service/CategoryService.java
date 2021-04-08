package com.kintexgroup.hkpsi.information.service;

import com.kintexgroup.hkpsi.information.model.CategoryDTO;
import com.kintexgroup.hkpsi.information.model.CategoryVO;
import com.kintexgroup.hkpsi.information.model.SkuAttributeDTO;

import java.util.List;

/**
 * @author lmao
 * @since 2020/9/8 14:57
 */
public interface CategoryService {

    /**
     * 查询多条的类别记录
     *
     * @return 多条的类别记录
     */
    List<CategoryVO> selectMany();

    /**
     * 查询单条的类别记录
     *
     * @param id 类别 id
     * @return 单条的类别记录
     */
    CategoryVO selectOne(Integer id);

    /**
     * 新增一个分类
     *
     * @param dto 参数
     * @return AddCategoryResponseData
     */
    Boolean addCategory(CategoryDTO dto);

    /**
     * 更新一条类别记录
     *
     * @param id  类别 id
     * @param dto 更新的类别记录
     * @return
     */
    Boolean updateOne(Integer id, CategoryDTO dto);

    /**
     * 根据categoryId,批量更新SkuAttribute信息
     *
     * @param categoryId 类别id
     * @param dto
     * @return 成功 true/失败 false
     */
    Boolean update(Integer categoryId, List<SkuAttributeDTO> dto);

    /**
     * 删除一条类别记录
     *
     * @param id 类别 id
     * @return
     */
    boolean deleteOne(Integer id);


    /**
     * 根据分类id  联合spu表查询分类名
     *
     * @param id 分类id
     * @return 返回查数据出来的条数
     */
    Integer selectByCategory(int id);
}
