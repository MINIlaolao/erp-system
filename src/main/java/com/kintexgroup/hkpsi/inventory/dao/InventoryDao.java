package com.kintexgroup.hkpsi.inventory.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.inventory.entity.Inventory;
import com.kintexgroup.hkpsi.inventory.model.InventoryPageDTO;
import com.kintexgroup.hkpsi.inventory.model.InventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lmao
 * @since 2020/9/27 17:56
 */
@Mapper
public interface InventoryDao {

    /**
     * 批量更新
     *
     * @param list 更新的 list
     * @return 是否成功
     */
    int updateBatch(List<Inventory> list);

    /**
     * 根据 idList 查询对应的库存数
     *
     * @param idList ids
     * @return id, 库存数
     */
    List<Inventory> selectBatchInventoryQuantity(@Param("idList") String[] idList);

    /**
     * 查询所有
     *
     * @return id, 库存数
     */
    List<InventoryVO> selectAllVO();

    /**
     * 查询所有型号的库存数
     *
     * @param dto dto
     * @return 查询出来的信息
     */
    Page<InventoryVO> selectInventory(InventoryPageDTO dto);

    /**
     * 查询spu的库存金额
     *
     * @return 查询的每个sku的金额
     */
    List<InventoryVO> selectSkuPrice();

    /**
     * 根据不同查询条件查询库存信息
     *
     * @param dto dto
     * @return 查询出来的信息
     */
    Page<InventoryVO> selectSkuInventory(@Param("dto") InventoryPageDTO dto);
}