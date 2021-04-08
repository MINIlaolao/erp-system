package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.Vendor;
import com.kintexgroup.hkpsi.information.model.VendorDTO;
import com.kintexgroup.hkpsi.information.model.VendorVO;

/**
 * @author junhangs
 * @since 2020/8/26 14:42
 */
public final class VendorPojoConverter {

    private VendorPojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static Vendor toEntity(VendorDTO dto) {
        Vendor entity = new Vendor();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param vendor 实体类
     * @return 视图类
     */
    public static VendorVO fromVendor(Vendor vendor) {
        VendorVO vo = new VendorVO();
        vo.setId(vendor.getId());
        vo.setCode(vendor.getCode());
        vo.setName(vendor.getName());
        vo.setDisabled(vendor.getDisabled());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static Vendor toCreateEntity(Integer creator, VendorDTO dto) {
        Vendor entity = toEntity(dto);
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
    public static Vendor toUpdateEntity(Integer id, Integer updater, VendorDTO dto) {
        Vendor entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
