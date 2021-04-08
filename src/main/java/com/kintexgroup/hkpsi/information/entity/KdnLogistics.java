package com.kintexgroup.hkpsi.information.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author LMAO
 * @since 2020/12/8 13:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hkpsi_kdn_logistics")
public class KdnLogistics implements Serializable {
    /**
     * 快递鸟物流编号id
     */
    @Id
    @Column(name = "kdn_logistics_id")
    @GeneratedValue(generator = "JDBC")
    private Integer kdnLogisticsId;

    /**
     * 物流公司名字
     */
    @Column(name = "kdn_logistics_name")
    private String kdnLogisticsName;

    /**
     * 快递鸟物流公司的代码
     */
    @Column(name = "kdn_logistics_code")
    private String kdnLogisticsCode;

    @Column(name = "disabled")
    private Integer disabled;

    private static final long serialVersionUID = 1L;
}