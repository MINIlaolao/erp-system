package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author pengli
 * @since 2020/11/24 3:07 下午
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ExchangeRatePageDTO extends BasePageDTO {

    /**
     * 货币代码
     */
    private String currencyCode;

    /**
     * 货币名称
     */
    private String currencyName;

    /**
     * 货币符号
     */
    private String currencySymbol;

    /**
     * 汇率
     */
    private Double exchangeRate;

    private Integer disabled;
}
