package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/5 11:18
 */
@Data
public class GoodsSkuModel {
    private String skuId;
    private Object attribute;
    private String spuId;
    private Double price;
    private String modelName;
    private String brandName;
    private String category;
    private List<Double> historyPrice;
    /**
     * 销售时间
     */
    private List<String> date;
}


