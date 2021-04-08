package com.kintexgroup.hkpsi.inventory.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.inventory.model.InventoryPageDTO;
import com.kintexgroup.hkpsi.inventory.model.InventoryVO;

import java.util.List;

/**
 * @author lmao
 * @since 2020/9/27 17:56
 */
public interface InventoryService {

    /**
     * 查询所有 sku 对应库存
     *
     * @return 所有 sku 对应库存
     */
    List<InventoryVO> getAllInventory();

    /**
     * 查询所有型号的库存
     *
     * @param dto  dto
     * @param page 当前页数
     * @param size 每页的数量
     * @return 查到的型号名，品牌，库存
     */
    PageInfo<InventoryVO> getAllModelInventory(InventoryPageDTO dto, int page, int size);

    /**
     * 查询的库存信息
     *
     * @param dto  dto
     * @param page 当前页数
     * @param size 每页的数量
     * @return 查询出来的结果
     */
    PageInfo<InventoryVO> getModelInventoryByOther(InventoryPageDTO dto, int page, int size);
}
