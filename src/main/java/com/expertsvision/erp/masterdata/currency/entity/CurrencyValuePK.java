package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the currency_values database table.
 * 
 */
@Embeddable
public class CurrencyValuePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="value")
	private Integer value;

	public CurrencyValuePK() {
	}
	public String getCurrencyCode() {
		return this.currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Integer getValue() {
		return this.value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CurrencyValuePK)) {
			return false;
		}
		CurrencyValuePK castOther = (CurrencyValuePK)other;
		return 
			this.currencyCode.equals(castOther.currencyCode)
			&& this.value.equals(castOther.value);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.currencyCode.hashCode();
		hash = hash * prime + this.value.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "CurrencyValuePK [currencyCode=" + currencyCode + ", value=" + value + "]";
	}
	
}