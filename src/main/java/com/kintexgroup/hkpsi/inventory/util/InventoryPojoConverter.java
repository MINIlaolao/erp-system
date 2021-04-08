package com.kintexgroup.hkpsi.inventory.util;

import com.kintexgroup.hkpsi.inventory.model.InventoryDTO;
import com.kintexgroup.hkpsi.inventory.entity.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @since 2020/9/30 10:12
 */

public final class InventoryPojoConverter {

    private InventoryPojoConverter() {
    }

    private static Inventory toEntity(InventoryDTO dto) {
        Inventory inventory = new Inventory();

        inventory.setInventoryQuantity(dto.getInventoryQuantity());
        inventory.setSkuId(dto.getSkuId());

        return inventory;
    }

    public static Inventory toCreateInventory(InventoryDTO dto, int creator) {
        var entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    public static Inventory toUpdateInventory(InventoryDTO dto, int updater) {
        var entity = toEntity(dto);
        entity.setUpdatedBy(updater);
        return entity;
    }

    public static Map<String, Object> fromEntity(Inventory entity) {
        var map = new HashMap<String, Object>(16);
        map.put("skuId", entity.getSkuId());
        map.put("quantity", entity.getInventoryQuantity());
        return map;
    }

    public static List<Object> batchFromEntity(List<Inventory> entities) {

        var out = new ArrayList<>(entities.size());
        entities.forEach(x -> out.add(fromEntity(x)));
        return out;
    }


    public static List<Inventory> updateBatchDTO(List<InventoryDTO> dtoList, int updater) {

        var out = new ArrayList<Inventory>(dtoList.size());

        dtoList.forEach(x -> out.add(toUpdateInventory(x, updater)));

        return out;
    }

}
