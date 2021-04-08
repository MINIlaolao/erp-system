package com.kintexgroup.hkpsi.purchasing.model.bidrecord;

import lombok.Data;

/**
 * @author pengli, junhangs
 */
@Data
public class BidRecordVO {

    /**
     * record id
     */
    private String id;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 外观成色
     */
    private String grade;

    /**
     * description | tag
     */
    private String tag;

    /**
     * vendor sku id
     */
    private String vendorSkuId;

    /**
     * 投标编号
     */
    private String bidId;

    /**
     * 投标数量
     */
    private Integer bidQty;

    /**
     * 投标单价
     */
    private Double bidPrice;

    /**
     * 投标合计
     */
    private String bidSum;

    /**
     * 投标港币
     */
    private Double bidHkd;
    
    /**
     * 中标id
     */
    private String wonId;

    /**
     * 中标数量
     */
    private Integer wonQty;

    /**
     * 中标单价
     */
    private Double wonPrice;

    /**
     * 中标港币
     */
    private Double wonHkd;
}
