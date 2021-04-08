package com.kintexgroup.hkpsi.information.entity;

import java.util.Date;

/**
 * @author pengli
 * @since 2020/11/13 10:52 上午
 */
public class ExchangeRate {

    /**
     * 汇率id
     */
    private Integer id;

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
    private Double exchangeRateDouble;

    private Integer disabled;

    private Date createdAt;

    /**
     * 创建人ID
     */
    private Integer createdBy;

    /**
     * 修改时间 数据表自动生成
     */
    private Date updatedAt;

    /**
     * 修改人ID
     */
    private Integer updatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public Double getExchangeRateDouble() {
        return exchangeRateDouble;
    }

    public void setExchangeRateDouble(Double exchangeRateDouble) {
        this.exchangeRateDouble = exchangeRateDouble;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
