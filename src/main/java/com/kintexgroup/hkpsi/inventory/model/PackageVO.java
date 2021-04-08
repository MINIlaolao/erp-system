package com.kintexgroup.hkpsi.inventory.model;

import com.kintexgroup.hkpsi.inventory.entity.InventoryGoodsEntity;
import lombok.Data;

/**
 * @author gradylo
 */
@Data
public class PackageVO {

    /**
     * 包装id
     */
    private String id;

    /**
     * 包装编号
     */
    private String packageNo;

    /**
     * 包装内信息
     */
    private String content;

    /**
     * 设备信息
     */
    InventoryGoodsEntity[] imeiRecordList;

}