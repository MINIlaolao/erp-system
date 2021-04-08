package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.SkuAttribute;
import com.kintexgroup.hkpsi.information.model.SkuAttributeDTO;
import com.kintexgroup.hkpsi.information.model.SkuAttributeVO;

import java.util.ArrayList;
import java.util.List;


/**
 * @author pengli
 * @since 2020/9/15 7:10 下午
 */
public final class SkuAttributePojoConverter {

    private SkuAttributePojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static SkuAttribute toEntity(SkuAttributeDTO dto) {
        SkuAttribute entity = new SkuAttribute();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param skuKey 实体类
     * @return 视图类
     */
    public static SkuAttributeVO fromSku(SkuAttribute skuKey) {
        SkuAttributeVO vo = new SkuAttributeVO();
        vo.setId(skuKey.getId());
        vo.setCategoryId(skuKey.getCategoryId());
        vo.setName(skuKey.getName());
        vo.setDescription(skuKey.getDescription());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param category
     * @param dto      参数类
     * @return 实体类
     */
    public static SkuAttribute toCreateEntity(Integer category, SkuAttributeDTO dto) {
        SkuAttribute entity = toEntity(dto);
        entity.setCategoryId(category);
        return entity;
    }


    public static List<SkuAttribute> toEntityList(List<SkuAttributeDTO> dtoLists) {
        var list = new ArrayList<SkuAttribute>(dtoLists.size());
        dtoLists.forEach(record -> list.add(toUpdateEntity(record)));
        return list;
    }

    /**
     * 用于更新的实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    public static SkuAttribute toUpdateEntity(SkuAttributeDTO dto) {
        SkuAttribute entity = toEntity(dto);
        entity.setId(dto.getId());
        entity.setCategoryId(dto.getCategoryId());
        return entity;
    }
}
