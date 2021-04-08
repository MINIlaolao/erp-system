package com.kintexgroup.hkpsi.inventory.service;

import com.kintexgroup.hkpsi.inventory.model.InventoryGoodsWithoutPrice;

/**
 * @author hzgotb
 * @since 2021/01/20 15:26
 */
public interface InventoryGoodsService {
    Object[] list(String[] args);

    /**
     * 
     * @param code source_code
     * @param imei IMEI
     * @param sn Serial Number
     */
    InventoryGoodsWithoutPrice info(String id, String code, String imei, String sn);
    
    String getLotIdByIMEI(String imei);
}
