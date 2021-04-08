package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/24 5:22 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GoodsPageDTO extends BasePageDTO {

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
