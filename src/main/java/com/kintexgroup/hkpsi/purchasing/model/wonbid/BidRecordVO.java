package com.kintexgroup.hkpsi.purchasing.model.wonbid;

import lombok.Data;

/**
 * @author lmao
 * @since 2020/10/10 11:24
 */
@Data
public class BidRecordVO {

    /**
     * 描述信息
     */
    private String description;

    /**
     * 外观成色
     */
    private String grade;

    /**
     * skuid
     */
    private Integer skuId;

    /**
     * 投标数量
     */
    private Integer quantity;

    /**
     * 中标数量
     */
    private Integer wonQty;

    /**
     * 投标单价
     */
    private Double bidPrice;

    /**
     * 投标港币
     */
    private Double hkd;

    /**
     * 中标单价
     */
    private Double wonPrice;

    /**
     * 中标港币
     */
    private Double wonHkd;

    private String tag;
}


