package com.kintexgroup.hkpsi.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author LMAO
 * @since 2020/12/11 10:14
 */

/**
 * 到货记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hkpsi_arrival_record")
public class ArrivalRecord implements Serializable {
    /**
     * 到货记录id
     */
    @Id
    @Column(name = "record_id")
    @GeneratedValue(generator = "JDBC")
    private String recordId;

    /**
     * 中标单号
     */
    @Column(name = "arrival_order_number")
    private String arrivalOrderNumber;

    /**
     * 到货日期
     */
    @Column(name = "arrival_date")
    private String arrivalDate;

    /**
     * 货运单号
     */
    @Column(name = "arrival_tracking")
    private String arrivalTracking;

    /**
     * 货运公司
     */
    @Column(name = "arrival_carrier")
    private String arrivalCarrier;

    /**
     * 件数
     */
    @Column(name = "arrival_pieces")
    private Integer arrivalPieces;

    /**
     * 箱/板
     */
    @Column(name = "arrival_carton_or_pallet")
    private String arrivalCartonOrPallet;

    /**
     * 重量(KG)
     */
    @Column(name = "arrival_weight")
    private Double arrivalWeight;

    /**
     * 签收人
     */
    @Column(name = "arrival_receiver")
    private String arrivalReceiver;

    /**
     * 时间
     */
    @Column(name = "arrival_delivery_time")
    private String arrivalDeliveryTime;

    /**
     * 破损
     */
    @Column(name = "arrival_damage")
    private String arrivalDamage;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    private static final long serialVersionUID = 1L;
}