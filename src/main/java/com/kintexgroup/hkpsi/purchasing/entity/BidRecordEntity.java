package com.kintexgroup.hkpsi.purchasing.entity;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pengli
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "hkpsi_bid_record")
public class BidRecordEntity extends BaseEntity {

    /**
     * 清单id
     */
    @Id
    @Column(name = "record_id")
    @GeneratedValue(generator = "JDBC")
    private String id;

    /**
     * 投标编号
     */
    @Column(name = "bid_id")
    private String bidId;

    /**
     * 投标数量
     */
    @Column(name = "bid_qty")
    private Integer bidQty;

    /**
     * 投标单价
     */
    @Column(name = "bid_price")
    private Double bidPrice;

    /**
     * 投标港币
     */
    @Column(name = "bid_hkd")
    private Double bidHkd;

    /**
     * 投标合计
     */
    @Column(name = "bid_sum")
    private Double bidSum;

    /**
     * 往期中标价
     */
    @Column(name = "bid_high")
    private Double bidHigh;

    /**
     * 中标id
     */
    @Column(name = "won_id")
    private String wonId;

    /**
     * 中标数量
     */
    @Column(name = "won_qty")
    private Integer wonQty;

    /**
     * 中标单价
     */
    @Column(name = "won_price")
    private Double wonPrice;

    /**
     * 中标港币
     */
    @Column(name = "won_hkd")
    private Double wonHkd;

    /**
     * 中标合计
     */
    @Column(name = "won_sum")
    private Double wonSum;
    
    @Column(name = "tag")
    private String tag;
    
    @Column(name = "source_warehouse")
    private String sourceWarehouse;
    
    @Column(name = "source_sku")
    private String sourceSKU;
    
    @Column(name = "source_oem")
    private String sourceOEM;
    
    @Column(name = "source_description")
    private String sourceDescription;
    
    @Column(name = "source_model_number")
    private String sourceModelNumber;
    
    @Column(name = "source_model_name")
    private String sourceModelName;
    
    @Column(name = "source_model")
    private String sourceModel;
    
    @Column(name = "source_capacity")
    private String sourceCapacity;
    
    @Column(name = "source_carrier")
    private String sourceCarrier;
    
    @Column(name = "source_color")
    private String sourceColor;
    
    @Column(name = "source_condition")
    private String sourceCondition;
    
    @Column(name = "source_grade")
    private String sourceGrade;
    
    @Column(name = "source_FMiP_locked")
    private String sourceFMiPLocked;
    
    @Column(name = "source_SIM_locked")
    private String sourceSIMLocked;
    
    @Column(name = "source_LCD_health")
    private String sourceLCDHealth;
}
