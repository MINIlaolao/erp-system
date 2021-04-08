package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/1 15:35
 */
@Data
public class BidUtilVO {
    private String currencyCode;
    private List<ResultItemAboutQueryTheBidPrice> list;
    
}


