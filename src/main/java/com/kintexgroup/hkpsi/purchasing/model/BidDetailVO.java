package com.kintexgroup.hkpsi.purchasing.model;

import lombok.Data;

import java.util.Map;

/**
 * @author junhangs
 * @since 2020/9/12 13:51
 */
@Data
public class BidDetailVO {

    private String id;
    private String date;
    private String currencyCode;
    private Double exchangeRate;
    private Map<String, Object> vendor;
    private Map<String, Object> won;
}