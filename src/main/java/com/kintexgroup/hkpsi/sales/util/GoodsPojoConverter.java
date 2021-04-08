package com.kintexgroup.hkpsi.sales.util;

import com.kintexgroup.hkpsi.sales.model.GoodsVO;
import com.kintexgroup.hkpsi.sales.entity.Goods;

/**
 * @author pengli
 * @since 2020/11/7 6:33 下午
 */
public final class GoodsPojoConverter {

    private GoodsPojoConverter() {
    }


    /**
     * 将从数据库获取到的实体类 转为发送给前端的视图类
     *
     * @param goods 实体类
     * @return 视图类
     */
    public static GoodsVO fromGoods(Goods goods) {
        GoodsVO vo = new GoodsVO();
        vo.setSaleOrder(goods.getSaleOrder());
        vo.setPrice(goods.getPrice());
        vo.setImei1(goods.getImei1());
        vo.setImei2(goods.getImei2());
        vo.setLocked(goods.getLocked());
        vo.setICloud(goods.getIcloud());
        vo.setDeviceInTime(goods.getDeviceInTime());
        vo.setDeviceOutTime(goods.getDeviceOutTime());
        vo.setDisabled(goods.getDisabled());
        vo.setAttribute(goods.getAttribute());
        vo.setModelName(goods.getModelName());
        vo.setModel(goods.getModel());
        vo.setCapacity(goods.getCapacity());
        vo.setRemark(goods.getRemark());
        vo.setBrandName(goods.getBrandName());
        vo.setCategory(goods.getCategory());
        return vo;
    }
}
