package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.Category;
import com.kintexgroup.hkpsi.information.model.CategoryDTO;
import com.kintexgroup.hkpsi.information.model.CategoryVO;

/**
 * @author lmao
 * @since 2020/9/8 15:12
 */
public final class CategoryPojoConverter {

    private CategoryPojoConverter() {
    }

    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param entity 实体类
     * @return 视图类
     */
    public static CategoryVO fromCategory(Category entity) {
        var vo = new CategoryVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setDisabled(entity.getDisabled());
        return vo;
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    public static Category toEntity(CategoryDTO dto) {
        var entity = new Category();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static Category toCreate(int creator, CategoryDTO dto) {
        var entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param id      要更新的实体类id
     * @param updater 更新人的id
     * @param dto     参数类
     * @return 实体类
     */
    public static Category toUpdate(Integer id, int updater, CategoryDTO dto) {
        var entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }

}
