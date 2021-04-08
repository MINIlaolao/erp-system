package com.kintexgroup.hkpsi.inventory.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.kintexgroup.hkpsi.common.handler.CommonPageConverter;
import com.kintexgroup.hkpsi.inventory.dao.InventoryDao;
import com.kintexgroup.hkpsi.inventory.model.InventoryPageDTO;
import com.kintexgroup.hkpsi.inventory.model.InventoryVO;
import com.kintexgroup.hkpsi.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @author pengli
 * @since 2020/9/27 17:56
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryDao inventoryDao;

    @Override
    public List<InventoryVO> getAllInventory() {
        return inventoryDao.selectAllVO();
    }

    @Override
    public PageInfo<InventoryVO> getAllModelInventory(InventoryPageDTO dto, int page, int size) {
        PageMethod.startPage(page, size, true);
        // 计算spu的库存数
        var records = inventoryDao.selectInventory(dto);
        // 查询出商品表中没有售出的sku的中标金额
        var prices = inventoryDao.selectSkuPrice();
        //把查询出来的sku的spu_name作为键，won_price作为值存到map中
        Map<String, Double> map = new HashMap<>(16);

        Double value;
        for (InventoryVO price : prices) {
            String modelName = price.getModelName();
            String key = map.keySet().toString();
            // 判断取出来的ModelName在map的key中是否存在 存在就取出这个key对应的value，再加上取出来的countPrice 作为新的value值
            if (key.contains(modelName)) {
                map.put(modelName, map.get(modelName) + price.getCountPrice());
            } else {
                value = 0.0;
                map.put(modelName, price.getCountPrice() + value);
            }
        }

        var list = new ArrayList<InventoryVO>();
        records.forEach(record -> {
            String key = map.keySet().toString();
            //用contains（）判断key中是否包含取出来的ModelName  有的话  把对应key的value取出来 作为 库存金额
            if (key.contains(record.getModelName())) {
                record.setCountPrice(Double.parseDouble(map.get(record.getModelName()).toString()));
            }
            list.add(record);
        });

        return CommonPageConverter.toPageInfo(records, list);
    }

    @Override
    public PageInfo<InventoryVO> getModelInventoryByOther(InventoryPageDTO dto, int page, int size) {
        PageMethod.startPage(page, size, true);
        var records = inventoryDao.selectSkuInventory(dto);
        var list = new ArrayList<>(records);
        return CommonPageConverter.toPageInfo(records, list);
    }
}
