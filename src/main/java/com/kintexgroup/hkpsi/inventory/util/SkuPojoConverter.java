package com.kintexgroup.hkpsi.inventory.util;


import com.kintexgroup.hkpsi.inventory.entity.Sku;
import com.kintexgroup.hkpsi.inventory.model.SkuDTO;
import com.kintexgroup.hkpsi.inventory.model.SkuVO;

/**
 * @author lmao
 */
public final class SkuPojoConverter {

    private SkuPojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static Sku toEntity(SkuDTO dto) {
        Sku entity = new Sku();
        entity.setSpuId(dto.getSpuId());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param spu 实体类
     * @return 视图类
     */
    public static SkuVO fromSpu(Sku spu) {
        SkuVO vo = new SkuVO();
        vo.setId(spu.getId());
        vo.setSpuId(spu.getSpuId());
        vo.setQuantity(spu.getQuantity());
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
    public static Sku toCreateEntity(Integer creator, SkuDTO dto) {
        Sku entity = toEntity(dto);
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
    public static Sku toUpdateEntity(Integer id, Integer updater, SkuDTO dto) {
        Sku entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
