package com.kintexgroup.hkpsi.purchasing.model.bidrecord;

import com.kintexgroup.hkpsi.purchasing.entity.BidRecordEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LMAO
 * @since 2020/11/19 15:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BidRecordSalesList extends BidRecordEntity {
    private Double singlePrice;
    private String salesman;
    private String clientName;
    private Integer saleQuantity;
    private Double exchangeRate;
    private String currencyCode;
    private Integer totalSkuCount;
    private String saleOrderId;
    private String item;
}


