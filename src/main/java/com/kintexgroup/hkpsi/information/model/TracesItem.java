package com.kintexgroup.hkpsi.information.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lmao
 */
@Data
public class TracesItem implements Serializable {

    private static final long serialVersionUID = -6967515798434554167L;
    @JSONField(name = "Action")
    private String action;

    @JSONField(name = "AcceptStation")
    private String acceptStation;

    @JSONField(name = "AcceptTime")
    private String acceptTime;

}