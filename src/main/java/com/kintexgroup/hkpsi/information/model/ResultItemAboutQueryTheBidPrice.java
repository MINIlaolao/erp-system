package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author LMAO
 * @since 2020/12/1 15:37
 */
@Data
public class ResultItemAboutQueryTheBidPrice {
    private ConditionsAboutQueryTheBidPriceDTO conditions;
    private ArrayList<HistoryAboutTheBid> histories;
}




