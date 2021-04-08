package com.kintexgroup.hkpsi.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.kintexgroup.hkpsi.information.model.LogisticsResult;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author LMAO
 * @since 2020/12/8 13:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hkpsi_logistics")
public class Logistics implements Serializable {
    /**
     * 物流id
     */
    @Id
    @Column(name = "logistics_id")
    @GeneratedValue(generator = "JDBC")
    private String logisticsId;

    /**
     * 中标单号
     */
    @Column(name = "won_number")
    private String wonNumber;

    /**
     * 物流单号
     */
    @Column(name = "logistics_number")
    private String logisticsNumber;

    /**
     * 物流公司代码
     */
    @Column(name = "kdn_logistics_id")
    private Integer kdnLogisticsId;


    private LogisticsResult logisticsResult;
    /**
     * 是否可用
     */
    @Column(name = "disabled")
    private Integer disabled;

    private static final long serialVersionUID = 1L;
}