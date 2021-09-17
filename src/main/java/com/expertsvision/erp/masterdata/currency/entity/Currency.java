package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name="currency")
@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="currency_d_name")
	private String currencyDName;

	@Column(name="currency_f_name")
	private String currencyFName;

	@Column(name="exchange_rate")
	private BigDecimal exchangeRate;

	@Column(name="fraction_d_name")
	private String fractionDName;

	@Column(name="fraction_f_name")
	private String fractionFName;

	@Column(name="fraction_no")
	private Integer fractionNo;

	@Column(name="local_currency")
	private Boolean localCurrency;

	@Column(name="max_ex_rate")
	private BigDecimal maxExRate;

	@Column(name="min_ex_rate")
	private BigDecimal minExRate;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="pos_ex_rate")
	private BigDecimal posExRate;

	public Currency() {
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Integer getAddUser() {
		return this.addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public String getCurrencyDName() {
		return this.currencyDName;
	}

	public void setCurrencyDName(String currencyDName) {
		this.currencyDName = currencyDName;
	}

	public String getCurrencyFName() {
		return this.currencyFName;
	}

	public void setCurrencyFName(String currencyFName) {
		this.currencyFName = currencyFName;
	}

	public String getFractionDName() {
		return this.fractionDName;
	}

	public void setFractionDName(String fractionDName) {
		this.fractionDName = fractionDName;
	}

	public String getFractionFName() {
		return this.fractionFName;
	}

	public void setFractionFName(String fractionFName) {
		this.fractionFName = fractionFName;
	}

	public Integer getFractionNo() {
		return this.fractionNo;
	}

	public void setFractionNo(Integer fractionNo) {
		this.fractionNo = fractionNo;
	}

	public Boolean getLocalCurrency() {
		return this.localCurrency;
	}

	public void setLocalCurrency(Boolean localCurrency) {
		this.localCurrency = localCurrency;
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

	public BigDecimal getPosExRate() {
		return posExRate;
	}

	public void setPosExRate(BigDecimal posExRate) {
		this.posExRate = posExRate;
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


	@Override
	public String toString() {
		return "Currency [currencyCode=" + currencyCode + ", addDate=" + addDate + ", addUser=" + addUser
				+ ", currencyDName=" + currencyDName + ", currencyFName=" + currencyFName + ", exchangeRate="
				+ exchangeRate + ", fractionDName=" + fractionDName + ", fractionFName=" + fractionFName
				+ ", fractionNo=" + fractionNo + ", localCurrency=" + localCurrency + ", maxExRate=" + maxExRate
				+ ", minExRate=" + minExRate + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", posExRate=" + posExRate + "]";
	}

}