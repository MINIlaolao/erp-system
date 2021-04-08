package com.kintexgroup.hkpsi.sales.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LMAO
 * @since 2020/11/17 17:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRecordCustom {

    private int quantity;

    private Double singlePrice;
    /**
     * 单个 imei 用来查询成本
     */
    private String imei;
}


