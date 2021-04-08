package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LMAO
 * @since 2020/12/5 11:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsSkuVO extends GoodsSkuModel {
    private String price1;
    private String price2;
    private String price3;
}


