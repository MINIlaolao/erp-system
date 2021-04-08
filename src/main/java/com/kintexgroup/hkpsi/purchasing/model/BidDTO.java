package com.kintexgroup.hkpsi.purchasing.model;

import com.kintexgroup.hkpsi.purchasing.model.bidrecord.BidRecordDTO;
import lombok.Data;

import java.util.List;

/**
 * @author junhangs
 * @since 2020/8/23 22:30
 */
@Data
public class BidDTO {

    /**
     * id :投标号
     */
    private String id;

    /**
     * 投标日期 `bid_date`
     */
    private String date;

    /**
     * 文件名 `file_name`
     */
    private String fileName;

    /**
     * 供应商 `vendor_id`
     */
    private Integer vendor;

    /**
     * 供应商 `vendor_program`
     */
    private Integer program;

    /**
     * 货币 `currency`
     */
    private String currency;

    /**
     * 投标汇率
     */
    private Double exchangeRate;

    /**
     * 投标文件中的每条记录
     */
    private List<BidRecordDTO> records;

}
