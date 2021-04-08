package com.kintexgroup.hkpsi.purchasing.model.wonbid;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lmao
 * @since 2020/10/10 11:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WonBidVendorVO extends WonBidVO {

    private Object bid;
}
