package com.kintexgroup.hkpsi.sales.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

import java.util.List;

/**
 * @author pengli
 * @since 2020/11/24 5:31 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SaleOrderSearchPageDTO extends BasePageDTO {
    /**
     * 订单号
     */
    private String id;

    /**
     * 销售人
     */
    private String salesman;

    /**
     * 销售时间
     */
    private List<String> date;

    /**
     * 付款时间
     */
    private List<String> payMoneyDate;

    /**
     * 客户姓名
     */
    private String clientName;

    /**
     * 总价
     */
    private Double countPrice;

    /**
     * 定金
     */
    private Double deposit;

    /**
     * 折扣
     */
    private Double handingCharge;

    /**
     * 其他收费
     */
    private Double otherPrice;

    /**
     * 备注
     */
    private String remark;
}
