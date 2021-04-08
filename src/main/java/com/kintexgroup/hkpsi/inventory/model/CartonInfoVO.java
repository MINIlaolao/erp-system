package com.kintexgroup.hkpsi.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author LMAO
 * @since 2021/1/29 20:13
 */
@Data
@AllArgsConstructor
public class CartonInfoVO {

    private String cartonNumber;

    private String date;

    private Object content;

    private List<InventoryGoodsWithoutPrice> list;

}


