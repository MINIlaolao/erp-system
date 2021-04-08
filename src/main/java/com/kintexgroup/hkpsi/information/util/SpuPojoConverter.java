package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.Spu;
import com.kintexgroup.hkpsi.information.model.SpuVO;
import com.kintexgroup.hkpsi.information.model.SpuDTO;

/**
 * @author pengli
 * @since 2020/9/8 3:13 下午
 */
public final class SpuPojoConverter {

    private SpuPojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static Spu toEntity(SpuDTO dto) {
        Spu entity = new Spu();
        entity.setName(dto.getName());
        entity.setBrand(dto.getBrand());
        entity.setCategory(dto.getCategory());
        entity.setSpec(dto.getSpec());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param spu 实体类
     * @return 视图类
     */
    public static SpuVO fromSpu(Spu spu) {
        SpuVO vo = new SpuVO();
        vo.setId(spu.getId());
        vo.setName(spu.getName());
        vo.setBrand(spu.getBrand());
        vo.setCategory(spu.getCategory());
        vo.setSpec(spu.getSpec());
        vo.setDisabled(spu.getDisabled());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static Spu toCreateEntity(Integer creator, SpuDTO dto) {
        Spu entity = toEntity(dto);
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
    public static Spu toUpdateEntity(Integer id, Integer updater, SpuDTO dto) {
        Spu entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
