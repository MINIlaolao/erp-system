package com.kintexgroup.hkpsi.inventory.dao;

import com.kintexgroup.hkpsi.inventory.entity.InventoryGoodsEntity;
import com.kintexgroup.hkpsi.inventory.model.InventoryGoodsWithoutPrice;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface InventoryGoodsDao extends Mapper<InventoryGoodsEntity> {
    InventoryGoodsWithoutPrice selectOneBySourceCode(String code);
    InventoryGoodsWithoutPrice selectOneByIMEI(String imei);
    String selectLotIdByIMEI(String imei);
    InventoryGoodsWithoutPrice selectOneBySerialNumber(String serialNumber);
    InventoryGoodsWithoutPrice selectOneById(String id);
    InventoryGoodsWithoutPrice selectOneByIdAndCode(String id, String code);
    InventoryGoodsEntity[] selectListByCodes(String[] codes);

    List<InventoryGoodsWithoutPrice> selectListByCarton(String id);

    InventoryGoodsWithoutPrice[] selectListWithoutPriceByCodes(String[] codes);
    int batchUpdateCartonId(String[] ids, String cartonId);
}