package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.Objects;

/**
 * @author LMAO
 * @since 2020/12/2 11:49
 */
@Data
public class RecordAboutQueryTheBidPrice {
    private String id;
    private Integer quantity;
    private Double price;
    private Double bidPrice;
    private String bidDate;
    private Double exchangeRate;
    private String currencySymbol;
    private String currency;
    private Double highPrice;
    private ConditionsAboutQueryTheBidPriceDTO conditions;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecordAboutQueryTheBidPrice that = (RecordAboutQueryTheBidPrice) o;
        return conditions.equals(that.conditions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditions);
    }

}


