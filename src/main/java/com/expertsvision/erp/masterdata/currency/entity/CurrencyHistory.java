package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the currency_history database table.
 * 
 */
@Entity
@Table(name="currency_history")
@IdClass(CurrencyHistoryPK.class)
@NamedQuery(name="CurrencyHistory.findAll", query="SELECT c FROM CurrencyHistory c")
public class CurrencyHistory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="currency_code")
	private String currencyCode;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_date")
	private Date modifyDate;

	@Column(name="exchange_rate")
	private BigDecimal exchangeRate;

	@Column(name="max_ex_rate")
	private BigDecimal maxExRate;

	@Column(name="min_ex_rate")
	private BigDecimal minExRate;

	@Column(name="modify_user")
	private Integer modifyUser;

	public CurrencyHistory() {
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getMaxExRate() {
		return maxExRate;
	}

	public void setMaxExRate(BigDecimal maxExRate) {
		this.maxExRate = maxExRate;
	}

	public BigDecimal getMinExRate() {
		return minExRate;
	}

	public void setMinExRate(BigDecimal minExRate) {
		this.minExRate = minExRate;
	}

	public Integer getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public String toString() {
		return "CurrencyHistory [currencyCode=" + currencyCode + ", modifyDate=" + modifyDate + ", exchangeRate="
				+ exchangeRate + ", maxExRate=" + maxExRate + ", minExRate=" + minExRate + ", modifyUser=" + modifyUser
				+ "]";
	}

}