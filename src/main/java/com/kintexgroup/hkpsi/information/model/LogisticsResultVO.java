package com.kintexgroup.hkpsi.information.model;

import lombok.Data;

import java.util.List;

/**
 * @author LMAO
 * @since 2020/12/8 17:13
 */
@Data
public class LogisticsResultVO {
    private String stateEx;

    private String logisticCode;

    private String shipperCode;

    private List<TracesItem> traces;

    private String state;

    private Boolean success;

    private String location;
}


