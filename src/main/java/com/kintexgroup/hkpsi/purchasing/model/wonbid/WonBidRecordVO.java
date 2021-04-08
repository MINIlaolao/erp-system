package com.kintexgroup.hkpsi.purchasing.model.wonbid;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author lmao
 * @since 2020/10/10 11:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WonBidRecordVO extends WonBidVO {

    private List<BidRecordVO> bidRecord;
}
