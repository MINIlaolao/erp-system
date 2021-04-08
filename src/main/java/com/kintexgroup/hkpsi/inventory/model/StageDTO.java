package com.kintexgroup.hkpsi.inventory.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lmao
 * @since 2020/8/26 17:22
 */
@Data
public class StageDTO implements Serializable {

    private static final long serialVersionUID = -1191767877785157958L;

    private String id;

    private String wonId;

    private Integer carrierId;

    private String whs;

    private String imei;

    private String vendorSku;

    private String tag;
}
