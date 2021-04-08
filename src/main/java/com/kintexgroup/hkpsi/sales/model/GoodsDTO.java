package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

/**
 * @author pengli
 * @since 2020/11/7 4:41 下午
 * <p>
 * 前端页面，销售下的商品中的  搜索字段
 */
@Data
public class GoodsDTO {

    private String imei1;

    /**
     * 类型
     */
    private String category;

    /**
     * 品牌
     */
    private String brandName;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 型号号码
     */
    private String model;

    /**
     * 颜色
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 订单编号
     */
    private String saleOrder;

    /**
     * 售出价格
     */
    private Double price;

    /**
     * 状态   是否可用
     */
    private Integer disabled;

}
