package com.kintexgroup.hkpsi.sales.service;

import com.github.pagehelper.PageInfo;
import com.kintexgroup.hkpsi.sales.model.SaleOrderSearchPageDTO;
import com.kintexgroup.hkpsi.sales.model.SaleOrderVO;
import com.kintexgroup.hkpsi.sales.model.SalesDTO;

import java.util.List;
import java.util.Map;

/**
 * @author lmao
 * @since 2020/9/27 16:06
 */
public interface SaleOrderService {

    /**
     * 添加一条订单信息
     *
     * @param record 一条订单信息
     * @return 一条订单信息
     */
    String insert(SalesDTO record);

    /**
     * 根据id删除一条订单信息
     *
     * @param id id
     * @return 成功：true | 失败 ：false
     */
    boolean delete(String id);

    /**
     * 根据ID更新一条订单信息
     *
     * @param id     id
     * @param record 一条订单信息
     * @return 更新后的一条订单信息
     */
    boolean update(String id, SalesDTO record);

    /**
     * 根据id查询一条订单信息
     *
     * @param id id
     * @return 查询的一条订单信息
     */
    SaleOrderVO selectOne(String id);

    /**
     * 查询所有订单信息
     *
     * @return 所有订单信息
     */
    List<SaleOrderVO> selectAll();

    /**
     * 查询单或多条记录
     *
     * @param dto      查询 dto
     * @param current  当前页面
     * @param pageSize 每页的数量
     * @return
     */
    PageInfo<SaleOrderVO> selectOneOrMany(SaleOrderSearchPageDTO dto, int current, int pageSize);

    /**
     * 更新付款时间和设备卖出时间
     *
     * @param saleOrderIdDate {"saleOrderId" : "1","payDateTime" : yyyy-MM-dd hh:mm:ss}
     */
    void pay(Map<String, String> saleOrderIdDate);
}
