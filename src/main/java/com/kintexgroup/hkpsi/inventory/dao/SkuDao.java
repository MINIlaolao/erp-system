package com.kintexgroup.hkpsi.inventory.dao;


import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.information.model.Attribute;
import com.kintexgroup.hkpsi.information.model.AttributeId;
import com.kintexgroup.hkpsi.inventory.entity.Sku;
import com.kintexgroup.hkpsi.inventory.model.SkuPageDTO;
import com.kintexgroup.hkpsi.sales.model.GoodsSkuModel;
import com.kintexgroup.hkpsi.sales.model.SkuPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author lmao
 * @since 2020/9/11 13:58
 */
@Repository
public interface SkuDao {


    /**
     * 添加一条Sku信息
     *
     * @param record 参数
     * @return 成功 : true | 失败 : false
     */
    boolean insert(Sku record);

    /**
     * 根据id删除一条Sku信息
     *
     * @param id 主键id
     * @return 成功 : true | 失败 : false
     */
    boolean deleteOne(Integer id);

    /**
     * 根据ID更新一条Sku信息
     *
     * @param record 参数
     * @return 成功 : true | 失败 : false
     */
    boolean updateOne(Sku record);

    /**
     * 检查数据表中会否存在这条数据
     *
     * @param spuId     主键id
     * @param attribute 属性
     * @return 有记录: true | 无记录 : false
     */
    boolean check(@Param("spuId") Integer spuId, @Param("attribute") Object attribute);


    /**
     * 按照 idList 批量查询的Sku
     *
     * @param idList    id 数组
     * @param saleOrder saleOrder
     * @return 结果集
     */
    List<GoodsSkuModel> batchSelect(@Param("idList") String[] idList);

    /**
     * 根据前端传进来的客户id,查询此客户近三次的销售单id,及销售时间
     *
     * @param clientId 顾客id
     * @return 查询的此客户的近三次销售单id的list集合
     */
    List<SkuPrice> selectSaleIdByClient(@Param("clientId") Integer clientId);

    /**
     * 根据前端传进来的idList查询goods表中，相同idList的价格
     *
     * @param idList   skuIdList
     * @param clientId 顾客id
     * @param saleId   销售单号集
     * @return 查询到的价格
     */
    List<SkuPrice> selectHistoryPriceBySkuId(@Param("idList") String[] idList,
                                             @Param("clientId") Integer clientId,
                                             @Param("saleId") List<Integer> saleId);


    /**
     * 批量插入
     *
     * @param skus skuList
     * @return 是否成功
     */
    boolean batchInsert(@Param("skus") List<Sku> skus);


    /**
     * 根据不同的查询条件 查询 分页查询
     *
     * @param dto 根据不同的查询条件的 bean
     * @return Page<Sku>
     */
    Page<Sku> selectOne(SkuPageDTO dto);

    /**
     * 批量 根据 spuId 更新 sku
     *
     * @param skus skus
     * @return 是否成功
     */
    boolean batchUpdateBySpuId(@Param("skus") List<Sku> skus);

    /**
     * 根据型号名查询skuId，attribute中的color，model，carrier，capacity
     *
     * @param modelName 型号名
     * @return 查到的结果
     */
    ArrayList<AttributeId> selectAttributeByModelName(String modelName);

    /**
     * 根据skuId批量查询
     *
     * @param idSet idSet
     * @return 查询到的结果
     */
    HashMap<String, Attribute> batchMapSelect(Set<String> idSet);
}