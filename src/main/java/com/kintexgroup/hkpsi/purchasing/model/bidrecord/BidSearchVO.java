package com.kintexgroup.hkpsi.purchasing.model.bidrecord;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/11/5 6:41 下午
 * <p>
 * 前端页面，投标管理的投标详情中展示的字段
 */
@Data
public class BidSearchVO {

    private String id;
    /**
     * 描述
     */
    private String description;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 成色
     */
    private String grade;

    /**
     * 投标数量
     */
    private Integer quantity;

    /**
     * 投标单价
     */
    private String bidPrice;

    /**
     * 中标单价
     */
    private String wonPrice;

    private String wonBidCountPrice;


}
