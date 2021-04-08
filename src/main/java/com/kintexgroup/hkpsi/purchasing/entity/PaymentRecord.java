package com.kintexgroup.hkpsi.purchasing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.kintexgroup.hkpsi.common.dto.BasePageDTO;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author LMAO
 * @since 2020/12/4 10:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hkpsi_payment_record")
public class PaymentRecord extends BasePageDTO implements Serializable {
    /**
     * 中标单号
     */
    @Id
    @Column(name = "won_number")
    private String wonNumber;

    /**
     * 付款日期
     */
    @Column(name = "pay_date")
    private String payDate;

    /**
     * 应付金额
     */
    @Column(name = "amount_payable")
    private Double amountPayable;

    /**
     * 使用余额
     */
    @Column(name = "use_balance")
    private Double useBalance;

    /**
     * 实付金额
     */
    @Column(name = "amount_paid")
    private Double amountPaid;

    /**
     * 操作人员
     */
    @Column(name = "`operator`")
    private Integer operator;

    private static final long serialVersionUID = 1L;
}