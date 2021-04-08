package com.kintexgroup.hkpsi.inventory.controller;

import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.inventory.model.InventoryGoodsWithoutPrice;
import com.kintexgroup.hkpsi.inventory.service.InventoryGoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hzgotb
 * @since 2021/01/20
 */

@RestController
@ResponseWrapper
@RequestMapping("/api/merchandise")
public class InventoryGoodsController {
    
    @Resource
    private InventoryGoodsService inventoryGoodsService;
    
    @PostMapping("/list")
    public Object[] list(@RequestBody String[] body){
        return inventoryGoodsService.list(body);
    }
    
    @GetMapping("/info")
    public InventoryGoodsWithoutPrice info(
        @RequestParam(value = "r", required = false) String id,
        @RequestParam(value = "c", required = false) String code,
        @RequestParam(value = "i", required = false) String imei,
        @RequestParam(value = "s", required = false) String sn
    ) {
        return inventoryGoodsService.info(id, code, imei, sn);
    }
    
    @GetMapping("/lot_id")
    public String lotId(@RequestParam("i") String imei) {
        return inventoryGoodsService.getLotIdByIMEI(imei);
    }
}
