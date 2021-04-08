package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.entity.VendorSkuEntity;
import com.kintexgroup.hkpsi.information.model.VendorSkuDTO;
import com.kintexgroup.hkpsi.information.model.VendorSkuVO;

/**
 * @author junhangs
 * @since 2020/9/11 11:56
 */
public class VendorSkuPojoConverter {

    private static VendorSkuEntity toEntity(VendorSkuDTO dto) {
        VendorSkuEntity entity = new VendorSkuEntity();
        entity.setDescription(dto.getDescription());
        entity.setGrade(dto.getGrade());
        entity.setTag(dto.getTag());
        entity.setVendor(dto.getVendor());
        entity.setKSku(dto.getKSku());
        entity.setDisabled(dto.getDisabled());
        return entity;
    }

//    public static VendorSkuVO fromEntity(Map<String, Object> map) {
//        //Builder
//        return VendorSkuVO.builder().sku(String.valueOf(map.get("kSku")))
//            .text((String) map.get("des"))
//            .vgrade((String) map.get("vGrade"))
//            .attribute(JsonUtil.toObject((String) map.get("attribute")))
//            .disabled((int) map.get("disabled"))
//            .vendor((String) map.get("vendor"))
//            .build();
//    }

    public static VendorSkuVO fromEntity(VendorSkuEntity vendorSku) {
        var vo = new VendorSkuVO();
        vo.setText(vendorSku.getDescription());
        vo.setVGrade(vendorSku.getGrade());
        vo.setTag(vendorSku.getTag());
        vo.setSku(vendorSku.getKSku());
        vo.setDisabled(vendorSku.getDisabled());
//        vo.setColor(vendorSku.getColor());
//        vo.setCarrier(vendorSku.getCarrier());
//        vo.setModel(vendorSku.getModel());
//        vo.setCapacity(vendorSku.getCapacity());
        vo.setGrade(vendorSku.getGrade());

        return vo;
    }


    public static VendorSkuEntity toCreateEntity(VendorSkuDTO dto, int creator) {
        var entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }
    
    public static VendorSkuEntity toUpdateEntity(String id, VendorSkuDTO dto, int updater) {
        VendorSkuEntity entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
