package com.kintexgroup.hkpsi.purchasing.model;

import com.kintexgroup.hkpsi.purchasing.entity.PaymentRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LMAO
 * @since 2020/12/5 17:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentRecordModel extends PaymentRecord {
    private String currencySymbol;
    private Double exchangeRate;
}


