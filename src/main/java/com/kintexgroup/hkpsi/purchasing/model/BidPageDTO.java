package com.kintexgroup.hkpsi.purchasing.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

import java.util.List;

/**
 * @author pengli
 * @since 2020/11/7 9:51 上午
 * 前端页面，采购下的投标管理中的搜索字段
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BidPageDTO extends BasePageDTO {

    /**
     * id :投标号
     */
    private String id;

    /**
     * 投标日期的开始
     */
    private String bidDateStart;

    /**
     * 投标日期的结束
     */
    private String bidDateEnd;

    /**
     * 供应商 vendorName
     */
    private String vendor;

    /**
     * 中标金额 `amount`
     */
    private Double amount;

    /**
     * 中标编号 `won_bid_number`
     */
    private String wonNumber;

    /**
     * 中标日期 `won_date` 的开始
     */
    private List<String> wonDateStart;
    
    /**
     * 中标日期 `won_date` 的结束
     */
    private List<String> wonDateEnd;
}
