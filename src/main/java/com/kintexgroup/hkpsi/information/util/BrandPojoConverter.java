package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.Brand;
import com.kintexgroup.hkpsi.information.model.BrandDTO;
import com.kintexgroup.hkpsi.information.model.BrandVO;

/**
 * @author pengli
 * @since 2020/9/8 6:28 下午
 */
public final class BrandPojoConverter {

    private BrandPojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static Brand toEntity(BrandDTO dto) {
        Brand entity = new Brand();
        entity.setName(dto.getName());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param inDatabaseRecord 实体类
     * @return 视图类
     */
    public static BrandVO fromEntity(Brand inDatabaseRecord) {
        BrandVO vo = new BrandVO();
        vo.setId(inDatabaseRecord.getId());
        vo.setName(inDatabaseRecord.getName());
        vo.setDisabled(inDatabaseRecord.getDisabled());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static Brand toCreateEntity(int creator, BrandDTO dto) {
        Brand entity = toEntity(dto);
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
    public static Brand toUpdateEntity(int id, int updater, BrandDTO dto) {
        Brand entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
