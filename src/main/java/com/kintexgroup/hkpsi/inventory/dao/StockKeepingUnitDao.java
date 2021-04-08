package com.kintexgroup.hkpsi.inventory.dao;

import com.kintexgroup.hkpsi.inventory.entity.StockKeepingUnit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StockKeepingUnit Mapper
 *
 * @author lmao
 */
@Repository
public interface StockKeepingUnitDao {

    /**
     * 根据 id 删除单条记录
     *
     * @param id sku编号
     * @return 成功 : true | 失败 : false
     */
    boolean deleteOne(Integer id);


    /**
     * 选择性插入 sku 记录
     *
     * @param record 插入的 sku 记录
     * @return 成功 : true | 失败 : false
     */
    boolean insertOne(StockKeepingUnit record);


    /**
     * 检查是否有重复的 TEXT
     *
     * @param text 描述信息
     * @return 检查出的 sku 记录
     */
    boolean checkDuplicate(String text);

    /**
     * 根据 id 查询单条记录
     *
     * @param id sku编号
     * @return 单条 Sku 记录
     */
    StockKeepingUnit selectOne(Integer id);

    /**
     * 查询所有 sku 记录
     *
     * @return 所有 Sku 记录
     */
    List<StockKeepingUnit> selectMany();

    /**
     * 选择性更新 sku 记录
     *
     * @param record 更新的 sku 记录
     * @return 成功 : true | 失败 : false
     */
    boolean updateOne(StockKeepingUnit record);

}