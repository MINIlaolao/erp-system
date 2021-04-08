package com.kintexgroup.hkpsi.inventory.service.impl;

import com.kintexgroup.hkpsi.common.util.ContextHolderUtil;
import com.kintexgroup.hkpsi.inventory.dao.InventoryGoodsDao;
import com.kintexgroup.hkpsi.inventory.model.InventoryGoodsWithoutPrice;
import com.kintexgroup.hkpsi.inventory.service.InventoryGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author hzgotb
 * @since 2020/01/20 15:28
 */

@Service("InventoryGoodsService")
public class InventoryGoodsServiceImpl implements InventoryGoodsService {
    
    @Resource
    private InventoryGoodsDao inventoryGoodsDao;
    
    @Override
    public Object[] list(String[] args) {
        List<Integer> access = ContextHolderUtil.getAuthedUserAccess();
        if (access.contains(300002)) {
            return inventoryGoodsDao.selectListByCodes(args);
        }
        return inventoryGoodsDao.selectListWithoutPriceByCodes(args);
    }

    @Override
    public InventoryGoodsWithoutPrice info(String id, String code, String imei, String sn) {
        if (id != null) {
            return inventoryGoodsDao.selectOneByIdAndCode(id, code);
        }
        if (code != null) {
            return inventoryGoodsDao.selectOneBySourceCode(code);
        }
        if (imei != null) {
            return inventoryGoodsDao.selectOneByIMEI(imei);
        }
        if (sn != null) {
            return inventoryGoodsDao.selectOneBySerialNumber(sn);
        }
        return null;
    }
    
    @Override
    public String getLotIdByIMEI(String imei) {
        return inventoryGoodsDao.selectLotIdByIMEI(imei);
    }
}
