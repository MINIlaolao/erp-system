package com.kintexgroup.hkpsi.inventory.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.kintexgroup.hkpsi.common.interfaces.ResponseWrapper;
import com.kintexgroup.hkpsi.inventory.model.InventoryPageDTO;
import com.kintexgroup.hkpsi.inventory.model.InventoryVO;
import com.kintexgroup.hkpsi.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lmao                  pengli
 * @since 2020/10/13 16:44       2020/11/20
 */
@RestController
@ResponseWrapper
@Api(tags = {"Inventory 库存"})
@RequestMapping("/api/inventory")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    @GetMapping("/inventoryList")
    public List<InventoryVO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/get_one_or_many_inventory")
    @ApiOperation(value = "根据查询条件查询一条或多条库存信息", notes = "查询条件为空时，查多条")
    public PageInfo<InventoryVO> getOneOrManyInventory(InventoryPageDTO dto) {
        return inventoryService.getAllModelInventory(dto, dto.getCurrent(), dto.getPageSize());
    }

    @GetMapping("/get_one_or_many_sku_inventory")
    @ApiOperation(value = "根据不同查询条件查询库存信息", notes = "根据不同查询条件查询库存信息")
    public PageInfo<InventoryVO> getOneOrManyInventoryByOther(InventoryPageDTO dto) {
        return inventoryService.getModelInventoryByOther(dto, dto.getCurrent(), dto.getPageSize());
    }
}


