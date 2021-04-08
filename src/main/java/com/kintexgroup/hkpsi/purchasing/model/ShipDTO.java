package com.kintexgroup.hkpsi.purchasing.model;

import lombok.Data;

/**
 * @author LMAO
 * @since 2020/12/8 15:09
 */
@Data
public class ShipDTO {
    private String wonNumber;
    /**
     * 物流单号
     */
    private String logisticCode;
    /**
     * 快递公司编码
     */
    private Integer kdnLogisticsId;
}


