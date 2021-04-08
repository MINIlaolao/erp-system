package com.kintexgroup.hkpsi.information.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author lmao
 */
@Data
public class LogisticsResult implements Serializable {

    @JSONField(name = "StateEx")
    private String stateEx;

    @JSONField(name = "LogisticCode")
    private String logisticCode;

    @JSONField(name = "ShipperCode")
    private String shipperCode;

    @JSONField(name = "Traces")
    private List<TracesItem> traces;

    @JSONField(name = "State")
    private String state;

    @JSONField(name = "EBusinessID")
    private String eBusinessId;

    @JSONField(name = "Success")
    private Boolean success;

    @JSONField(name = "Location")
    private String location;
}