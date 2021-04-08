package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author pengli
 * @since 2020/9/27 5:34 下午
 */
@Data
public class SalesDTO {


    /**
     * 客户id
     */
    private Integer clientId;

    /**
     * 销售人
     */
    private String salesman;

    /**
     * 总价
     */
    private Double countPrice;

    /**
     * 折扣
     */
    private Double handingCharge;


    /**
     * 定金
     */
    private Double deposit;


    /**
     * 其他收费
     */
    private Double otherPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 结款时间
     */
    private Date payMoneyDate;

    /**
     * 销售时间
     */
    private Date date;

    /**
     * 是否可用
     */
    private Boolean disabled;


    private Double exchangeRate;

    /**
     * goods api
     */
    @Size(min = 1)
    List<InvoiceRecord> invoice;
}
