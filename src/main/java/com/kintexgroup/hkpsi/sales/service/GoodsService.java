package com.kintexgroup.hkpsi.sales.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.sales.model.GoodsPageDTO;
import com.kintexgroup.hkpsi.sales.model.GoodsSkuModel;
import com.kintexgroup.hkpsi.sales.model.GoodsVO;
import com.kintexgroup.hkpsi.sales.model.SalesDTO;

import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @since 2020/9/27 16:08
 */
public interface GoodsService {

    /**
     * 根据扫码获取的序列号查询单个商品信息
     *
     * @param imei 序列号
     * @return 单个商品信息
     */
    Map<String, Object> getGoods(Long imei);

    /**
     * 根据skuIdArrays 查询 sku具体属性
     *
     * @param clientId    客户Id
     * @param skuIdArrays 前端传来的 skuId 数组
     * @return 属性表
     */
    List<GoodsSkuModel> getGoodsSku(String skuIdArrays, Integer clientId);


    /**
     * 接收前端的订单信息以及商品价格
     *
     * @param dto 订单信息及商品价格 { "skuId":"开单的 skuId", "price":"填入的价格", "quantity":"对应 skuId 的数量" }
     * @return 订单编号
     */
    String getBill(SalesDTO dto);

    /**
     * 查询所有 goods
     *
     * @return GoodsList
     */
    GoodsVO[] getAllGoods();

    /**
     * 根据订单 id 查询对应的商品信息
     *
     * @param saleOrderId 订单 saleOrderId
     * @return GoodsVO
     */
    Map<String, Object> getOneGoodsBySaleOrderId(String saleOrderId);

    /**
     * 根据不同的查询条件，查询一条或多条投标信息
     *
     * @param page page
     * @param size size
     * @param dto  dto
     * @return 查询出来的结果集
     */
    PageInfo<GoodsVO> selectOneOrManyGoods(GoodsPageDTO dto, int page, int size);
}
