package com.expertsvision.erp.masterdata.currency.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyViewFilter {

	@JsonProperty("currency_code")
	private String currencyCode;
	
	@JsonProperty("currency_d_name")
	private String currencyDName;

	@JsonProperty("currency_f_name")
	private String currencyFName;
	
	@JsonProperty("exchange_rate")
	private Integer exchangeRate;
	
	@JsonProperty("fraction_d_name")
	private String fractionDName;

	@JsonProperty("fraction_f_name")
	private String fractionFName;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyDName() {
		return currencyDName;
	}

	public void setCurrencyDName(String currencyDName) {
		this.currencyDName = currencyDName;
	}

	public String getCurrencyFName() {
		return currencyFName;
	}

	public void setCurrencyFName(String currencyFName) {
		this.currencyFName = currencyFName;
	}

	public Integer getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Integer exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getFractionDName() {
		return fractionDName;
	}

	public void setFractionDName(String fractionDName) {
		this.fractionDName = fractionDName;
	}

	public String getFractionFName() {
		return fractionFName;
	}

	public void setFractionFName(String fractionFName) {
		this.fractionFName = fractionFName;
	}

	@Override
	public String toString() {
		return "CurrencyViewFilter [currencyCode=" + currencyCode + ", currencyDName=" + currencyDName
				+ ", currencyFName=" + currencyFName + ", exchangeRate=" + exchangeRate + ", fractionDName="
				+ fractionDName + ", fractionFName=" + fractionFName + "]";
	}
	
}
