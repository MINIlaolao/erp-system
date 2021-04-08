package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LMAO
 * @since 2021/1/13 17:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HistoryAboutTheBid extends Price {

    private String wonBidDate;

    private Double highPrice;
}


