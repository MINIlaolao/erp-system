package com.kintexgroup.hkpsi.sales.dao;

import com.github.pagehelper.Page;
import com.kintexgroup.hkpsi.sales.model.SaleOrderSearchPageDTO;
import com.kintexgroup.hkpsi.sales.model.SaleOrderVO;
import com.kintexgroup.hkpsi.sales.entity.SaleOrder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author lmao and prngli
 * @since 2020/9/27 16:06
 */
@Repository
public interface SaleOrderDao {

    /**
     * 添加一条订单信息
     *
     * @param record 一条订单信息
     * @return 一条订单信息
     */
    boolean create(SaleOrder record);

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
     * @param record 一条订单信息
     * @return 更新后的一条订单信息
     */
    boolean update(SaleOrder record);

    /**
     * 根据id查询一条订单信息
     *
     * @param id id
     * @return 查询的一条订单信息
     */
    SaleOrder selectOne(String id);

    /**
     * 查询所有订单信息
     *
     * @return 所有订单信息
     */
    List<SaleOrderVO> selectAll();

    /**
     * 查询数据表中id=id有几个记录
     *
     * @param id id
     * @return 数据表中id=id的记录数
     */
    Integer checkId(String id);


    /**
     * 查询一条或多条
     *
     * @param dto dto
     * @return 查询到的信息
     */
    Page<SaleOrderVO> selectOneOrMany(SaleOrderSearchPageDTO dto);

    /**
     * 更新付款时间和设备卖出时间
     *
     * @param saleOrderId "saleOrderId" : "1"
     * @param date        "payDateTime" : yyyy-MM-dd hh:mm:ss
     */
    void updatePayTime(String saleOrderId, String date);

    /**
     * 付款时间是否为默认
     *
     * @param saleOrderId "saleOrderId" : "1"
     * @return 是否为默认
     */
    boolean isPayTimeDefault(String saleOrderId);

    String selectCountByDate(Date date);
}