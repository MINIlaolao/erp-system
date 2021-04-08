package com.kintexgroup.hkpsi.inventory.util;

import com.kintexgroup.hkpsi.inventory.entity.Stage;
import com.kintexgroup.hkpsi.inventory.model.StageDTO;
import com.kintexgroup.hkpsi.inventory.model.StageVO;
import com.kintexgroup.hkpsi.common.util.CommonUtil;
import com.kintexgroup.hkpsi.sales.entity.Goods;

import java.util.ArrayList;
import java.util.List;


/**
 * @author lmao             pengli
 * @since 2020/8/26 17:41   2020/10/09
 */
public final class StagePojoConverter {

    private StagePojoConverter() {
    }

    public static Stage toEntity(StageDTO dto) {
        var entity = new Stage();
        entity.setWonId(dto.getWonId());

        entity.setImei(dto.getImei());
        entity.setVendorSku(dto.getVendorSku());
        entity.setTag(dto.getTag());
        return entity;
    }

    public static Stage toCreateEntity(int creator, StageDTO dto) {
        var entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    public static Stage toUpdateEntity(String id, int updater, StageDTO dto) {
        var entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }

//    public static StageVO fromStage(Stage virtualWarehouse) {
//        var vo = new StageVO();
//        vo.setId(virtualWarehouse.getId());
//        vo.setWonId(virtualWarehouse.getWonId());
//        vo.setCarrierId(virtualWarehouse.getCarrierId());
//        vo.setWhs(virtualWarehouse.getWhs());
//        vo.setImei(virtualWarehouse.getImei());
//        vo.setVendorSku(virtualWarehouse.getVendorSku());
//        vo.setTag(virtualWarehouse.getTag());
//
//        return vo;
//    }

    public static StageVO fromStage(Stage stage) {
        var vo = new StageVO();
        vo.setBidId(stage.getBidId());
        vo.setImei(stage.getImei());
        vo.setText(stage.getText());
        vo.setBrand(stage.getBrand());
        vo.setModelName(stage.getModelName());
        vo.setModel(stage.getModel());
        vo.setColor(stage.getColor());
        vo.setCapacity(stage.getCapacity());
        vo.setGrade(stage.getVendorGrade());
        return vo;
    }

    public static List<Goods> toGoods(List<Stage> list) {
        List<Goods> goods = new ArrayList<>(list.size());
        for (Stage stage : list) {
            Goods good = new Goods();
            good.setSaleOrder("0");
            good.setWonBidNumber(Long.valueOf(stage.getWonId()));
            var skuId = stage.getVendorSku();
            CommonUtil.setValueIfNull(skuId, "0");
            good.setSkuId(skuId);
            good.setImei1(stage.getImei());
            good.setCreatedBy(stage.getCreatedBy());
            good.setUpdatedBy(stage.getUpdatedBy());
            goods.add(good);
        }
        return goods;
    }


}
