package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the currency_view database table.
 * 
 */
@Entity
@Table(name="currency_view")
@NamedQuery(name="CurrencyView.findAll", query="SELECT c FROM CurrencyView c")
public class CurrencyView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name="add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name="add_user_f_name")
	private String addUserFName;

	@Id
	@JsonProperty("currency_code")
	@Column(name="currency_code")
	private String currencyCode;

	@JsonProperty("currency_d_name")
	@Column(name="currency_d_name")
	private String currencyDName;

	@JsonProperty("currency_f_name")
	@Column(name="currency_f_name")
	private String currencyFName;

	@JsonProperty("exchange_rate")
	@Column(name="exchange_rate")
	private BigDecimal exchangeRate;

	@JsonProperty("fraction_d_name")
	@Column(name="fraction_d_name")
	private String fractionDName;

	@JsonProperty("fraction_f_name")
	@Column(name="fraction_f_name")
	private String fractionFName;

	@JsonProperty("fraction_no")
	@Column(name="fraction_no")
	private Integer fractionNo;

	@JsonProperty("local_currency")
	@Column(name="local_currency")
	private Boolean localCurrency;

	@JsonProperty("max_ex_rate")
	@Column(name="max_ex_rate")
	private BigDecimal maxExRate;

	@JsonProperty("min_ex_rate")
	@Column(name="min_ex_rate")
	private BigDecimal minExRate;

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

	@JsonProperty("pos_ex_rate")
	@Column(name="pos_ex_rate")
	private BigDecimal posExRate;
	
	@JsonProperty("currency_history_pages")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private MultiplePages<CurrencyHistoryView> currencyHistoryPages;
	
	@JsonProperty("currency_values_pages")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private MultiplePages<CurrencyValuesView> currencyValuesPages;

	public CurrencyView() {
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

	public String getAddUserDName() {
		return this.addUserDName;
	}

	public void setAddUserDName(String addUserDName) {
		this.addUserDName = addUserDName;
	}

	public String getAddUserFName() {
		return this.addUserFName;
	}

	public void setAddUserFName(String addUserFName) {
		this.addUserFName = addUserFName;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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

	public MultiplePages<CurrencyHistoryView> getCurrencyHistoryPages() {
		return currencyHistoryPages;
	}

	public void setCurrencyHistoryPages(MultiplePages<CurrencyHistoryView> currencyHistoryPages) {
		this.currencyHistoryPages = currencyHistoryPages;
	}

	public MultiplePages<CurrencyValuesView> getCurrencyValuesPages() {
		return currencyValuesPages;
	}

	public void setCurrencyValuesPages(MultiplePages<CurrencyValuesView> currencyValuesPages) {
		this.currencyValuesPages = currencyValuesPages;
	}

	@Override
	public String toString() {
		return "CurrencyView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", currencyCode=" + currencyCode + ", currencyDName="
				+ currencyDName + ", currencyFName=" + currencyFName + ", exchangeRate=" + exchangeRate
				+ ", fractionDName=" + fractionDName + ", fractionFName=" + fractionFName + ", fractionNo=" + fractionNo
				+ ", localCurrency=" + localCurrency + ", maxExRate=" + maxExRate + ", minExRate=" + minExRate
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName
				+ ", modifyUserFName=" + modifyUserFName + ", posExRate=" + posExRate + "]";
	}

}