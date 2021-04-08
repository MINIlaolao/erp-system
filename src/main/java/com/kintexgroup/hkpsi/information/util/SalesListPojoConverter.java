package com.kintexgroup.hkpsi.information.util;

import com.kintexgroup.hkpsi.information.model.SalesListVO;
import com.kintexgroup.hkpsi.information.entity.SalesList;
import org.springframework.validation.annotation.Validated;

/**
 * @author LMAO
 * @since 2020/11/18 18:24
 */
public class SalesListPojoConverter {
    private SalesListPojoConverter() {
    }

    public static SalesListVO fromEntity(@Validated SalesList entity) {
        SalesListVO vo = new SalesListVO();
        vo.setAsp(entity.getAsp());
        vo.setBrand(entity.getBrand());
        vo.setCapacity(entity.getCapacity());
        vo.setCarrier(entity.getCarrier());
        vo.setClientName(entity.getClientName());
        vo.setColor(entity.getColor());
        vo.setCostPrice(entity.getCostPrice());
        vo.setCountProfit(entity.getCountProfit());
        vo.setCountPrice(entity.getCountPrice());
        vo.setDescription(entity.getDescription());
        vo.setDisabled(entity.getDisabled());
        vo.setGpm(entity.getGpm());
        vo.setGrade(entity.getGrade());
        vo.setHkd(entity.getHkd());
        vo.setItem(entity.getItem());
        vo.setIcloud(entity.getIcloud());
        vo.setModelName(entity.getModelName());
        vo.setModelNumber(entity.getModelNumber());
        vo.setWarehouse(entity.getWarehouse());
        vo.setQuantity(entity.getQuantity());
        vo.setSaleDate(entity.getSaleDate());
        vo.setSaleOrderId(entity.getSaleOrderId());
        vo.setSalesman(entity.getSalesman());
        vo.setTotalCost(entity.getTotalCost());
        vo.setCostPrice(entity.getCostPrice());
        vo.setSimStatus(entity.getSimStatus());
        return vo;
    }
}


