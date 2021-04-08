package com.kintexgroup.hkpsi.purchasing.entity;

import com.kintexgroup.hkpsi.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * hkpsi_bid
 *
 * @author junhangs
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BidEntity extends BaseEntity {

    /**
     * 投标ID `bid_id`
     */
    private String id;

    /**
     * 投标日期 `bid_date`
     */
    private String date;

    /**
     * 供应商 `vendor_id`
     */
    private Integer vendor;

    /**
     * 供应商 `vendor_program`
     */
    private Integer program;

    /**
     * 文件名 `file_name`
     */
    private String fileName;

    /**
     * 货币 `currency`
     */
    private String currency;

    /**
     * 投标汇率
     */
    private Double exchangeRate;
}