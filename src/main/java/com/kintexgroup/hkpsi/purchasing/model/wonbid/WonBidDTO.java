package com.kintexgroup.hkpsi.purchasing.model.wonbid;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author lmao
 * @since 2020/9/9 09:36
 */
@Data
public class WonBidDTO {

    /**
     * 中标号 展示用
     */
    private String number;

    /**
     * 中标日期
     */
    private String date;

    /**
     * 中标金额
     */
    @DecimalMax(value = "9999999999999")
    private Double amount;


    /**
     * 投标 ID
     */
    @NotNull
    private String bidId;

    /**
     * 供应商
     */
    private String vendor;

    /**
     * 默认size = 0,如果不是全单中标的情况下,根据该字段的内容去更新投标记录
     */
    private List<TagUnitPriceBean> records;

    /**
     * 供应商仓库
     */
    private String warehouse;

    /**
     * 中标汇率
     */
    private Double exchangeRate;

}
