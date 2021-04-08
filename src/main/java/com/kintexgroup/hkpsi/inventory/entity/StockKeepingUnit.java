package com.kintexgroup.hkpsi.inventory.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 库存单位 hkpsi_stock_keeping_unit
 *
 * @author junhangs
 */
@Data
public class StockKeepingUnit
    implements Serializable {

    /**
     * sku编号
     */
    private Integer id;

    /**
     * 描述信息
     */
    private String text;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 型号名称
     */
    private String modelName;

    /**
     * 型号号码
     */
    private String modelNumber;

    /**
     * 区号, Part No. eg. A2275
     */
    private String regionCode;

    /**
     * 颜色名称
     */
    private String color;

    /**
     * 容量
     */
    private Integer capacity;

    /**
     * 内存
     */
    private Integer ram;

    /**
     * 运营商编号
     */
    private Integer carrierId;

    /**
     * 外观成色
     */
    private String whs;

    private Boolean disabled;

    /**
     * 创建时间
     */
    private Date createdAt;

    private Integer createdBy;

    /**
     * 修改时间
     */
    private Date updatedAt;

    private Integer updatedBy;

    private static final long serialVersionUID = 1L;

}