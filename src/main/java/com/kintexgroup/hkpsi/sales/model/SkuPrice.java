package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/12/8 7:40 下午
 */
@Data
public class SkuPrice {
    private String skuId;
    private Double price;

    /**
     * 销售单id
     */
    private Integer id;
    /**
     * 销售日期
     */
    private String date;
}
