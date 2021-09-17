package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the currency_history_view database table.
 * 
 */
@Entity
@IdClass(CurrencyHistoryPK.class)
@Table(name="currency_history_view")
@NamedQuery(name="CurrencyHistoryView.findAll", query="SELECT c FROM CurrencyHistoryView c")
public class CurrencyHistoryView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("currency_code")
	@Column(name="currency_code")
	private String currencyCode;

	@JsonProperty("exchange_rate")
	@Column(name="exchange_rate")
	private BigDecimal exchangeRate;

	@JsonProperty("max_ex_rate")
	@Column(name="max_ex_rate")
	private BigDecimal maxExRate;

	@JsonProperty("min_ex_rate")
	@Column(name="min_ex_rate")
	private BigDecimal minExRate;

	@Id
	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	public CurrencyHistoryView() {
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyUserDName() {
		return this.modifyUserDName;
	}

	public void setModifyUserDName(String modifyUserDName) {
		this.modifyUserDName = modifyUserDName;
	}

	public String getModifyUserFName() {
		return this.modifyUserFName;
	}

	public void setModifyUserFName(String modifyUserFName) {
		this.modifyUserFName = modifyUserFName;
	}

	@Override
	public String toString() {
		return "CurrencyHistoryView [currencyCode=" + currencyCode + ", exchangeRate=" + exchangeRate + ", maxExRate="
				+ maxExRate + ", minExRate=" + minExRate + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", modifyUserDName=" + modifyUserDName + ", modifyUserFName=" + modifyUserFName + "]";
	}

}