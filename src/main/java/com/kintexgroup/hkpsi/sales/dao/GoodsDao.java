package com.kintexgroup.hkpsi.sales.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.sales.model.GoodsPageDTO;
import com.kintexgroup.hkpsi.sales.model.GoodsVO;
import com.kintexgroup.hkpsi.sales.model.IdImeiBean;
import com.kintexgroup.hkpsi.sales.entity.Goods;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @since 2020/9/27 16:08
 */
public interface GoodsDao extends Mapper<Goods> {

    /**
     * 根据 imei 查询对应商品信息
     *
     * @param imei 序列号
     * @return 商品信息
     */
    HashMap<String, Object> selectGoods(@Param("imei") Long imei);

    /**
     * 批量更新 goods
     *
     * @param idImeiList idImeiList
     * @param map        goodsList
     * @return 是否成功
     */
    int updateBatchInvoiceSelective(@Param("map") Map<String, Goods> map,
                                    @Param("idImeiList") List<IdImeiBean> idImeiList);

    /**
     * 查询所有
     *
     * @return 结果集
     */
    List<GoodsVO> selectAllGoods();

    /**
     * 根据订单 id 查询对应的商品信息
     *
     * @param saleOrderId 订单 saleOrderId
     * @return GoodsVO
     */
    List<GoodsVO> selectOneBySaleOrderId(String saleOrderId);

    /**
     * 根据不同的查询条件，查询一条或多条中标记录。查询条件为空时 查所有
     *
     * @param dto dto
     * @return 查询出来的结果集
     */
    Page<Goods> selectOneOrMany(GoodsPageDTO dto);

    /**
     * 批量查询单件总价
     *
     * @param idImeiMap idImeiMap
     * @return 查询到的结果
     */
    Map<String, Double> batchSelectCostPrice(@Param("idImeiMap") Map<String, String> idImeiMap);

    /**
     * 根据saleOrderId查询
     *
     * @param saleOrderId saleOrderId
     * @return 计算好的手续费（没有乘以汇率）
     */
    double selectCountHandlingCharge(String saleOrderId);

    /**
     * 根据saleOrderId查询汇率
     *
     * @param saleOrderId saleOrderId
     * @return 汇率
     */
    double selectExchangeRate(String saleOrderId);

    /**
     * 检查是否存在imei
     *
     * @param entity 传入实体类
     * @return 是否存在
     */
    boolean checkImei(List<Goods> entity);
}