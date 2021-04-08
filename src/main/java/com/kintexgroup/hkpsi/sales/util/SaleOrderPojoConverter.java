package com.kintexgroup.hkpsi.sales.util;

import com.kintexgroup.hkpsi.sales.model.SaleOrderVO;
import com.kintexgroup.hkpsi.sales.model.SalesDTO;
import com.kintexgroup.hkpsi.sales.entity.SaleOrder;

/**
 * @author pengli
 * @since 2020/9/27 6:51 下午
 */
public final class SaleOrderPojoConverter {

    private SaleOrderPojoConverter() {
    }

    /**
     * 将从接口请求传入的参数类 转换为 实体类
     *
     * @param dto 参数类
     * @return 实体类
     */
    private static SaleOrder toEntity(SalesDTO dto) {
        SaleOrder entity = new SaleOrder();
        entity.setClientId(dto.getClientId());
        entity.setSalesman(dto.getSalesman());
        entity.setCountPrice(dto.getCountPrice());
        entity.setHandingCharge(dto.getHandingCharge());
        entity.setDeposit(dto.getDeposit());
        entity.setOtherPrice(dto.getOtherPrice());
        entity.setRemark(dto.getRemark());
        entity.setPayMoneyDate(dto.getPayMoneyDate());
        entity.setDate(dto.getDate());
        entity.setDisabled(dto.getDisabled());
        entity.setExchangeRate(dto.getExchangeRate());
        return entity;
    }


    /**
     * 将 从数据库获取到的实体类 转为发送给前端的 视图类
     *
     * @param saleOrder 实体类
     * @return 视图类
     */
    public static SaleOrderVO fromSaleOrder(SaleOrder saleOrder) {
        SaleOrderVO vo = new SaleOrderVO();
        vo.setId(saleOrder.getId());
        vo.setSalesman(saleOrder.getSalesman());
        vo.setCountPrice(saleOrder.getCountPrice());
        vo.setHandingCharge(saleOrder.getHandingCharge());
        vo.setDeposit(saleOrder.getDeposit());
        vo.setOtherPrice(saleOrder.getOtherPrice());
        vo.setRemark(saleOrder.getRemark());
        return vo;
    }

    /**
     * 用于创建的实体类
     *
     * @param creator 创建人id
     * @param dto     参数类
     * @return 实体类
     */
    public static SaleOrder toCreateEntity(Integer creator, SalesDTO dto) {
        SaleOrder entity = toEntity(dto);
        entity.setCreatedBy(creator);
        entity.setUpdatedBy(creator);
        return entity;
    }

    /**
     * 用于更新的实体类
     *
     * @param updater 更新人的id
     * @param dto     参数类
     * @return 实体类
     */
    public static SaleOrder toUpdateEntity(String id, Integer updater, SalesDTO dto) {
        SaleOrder entity = toEntity(dto);
        entity.setId(id);
        entity.setUpdatedBy(updater);
        return entity;
    }
}
