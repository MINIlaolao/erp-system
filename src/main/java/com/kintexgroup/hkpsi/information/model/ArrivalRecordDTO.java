package com.kintexgroup.hkpsi.information.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

/**
 * @author LMAO
 * @since 2020/12/11 10:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArrivalRecordDTO extends BasePageDTO {
    /**
     * 中标单号
     */
    private String arrivalOrderNumber;

    /**
     * 到货日期
     */
    private String arrivalDate;

    /**
     * 货运单号
     */
    private String arrivalTracking;

    /**
     * 货运公司
     */
    private String arrivalCarrier;

    /**
     * 件数
     */
    private Integer arrivalPieces;

    /**
     * 箱/板
     */
    private String arrivalCartonOrPallet;

    /**
     * 重量(KG)
     */
    private Double arrivalWeight;

    /**
     * 签收人
     */
    private String arrivalReceiver;

    /**
     * 时间
     */
    private String arrivalDeliveryTime;

    /**
     * 破损
     */
    private String arrivalDamage;
}


