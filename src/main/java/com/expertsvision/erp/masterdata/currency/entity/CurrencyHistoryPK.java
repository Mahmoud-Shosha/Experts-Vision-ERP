package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the currency_history database table.
 * 
 */
@Embeddable
public class CurrencyHistoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="currency_code", insertable=false, updatable=false)
	private String currencyCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modify_date")
	private java.util.Date modifyDate;

	public CurrencyHistoryPK() {
	}
	public String getCurrencyCode() {
		return this.currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CurrencyHistoryPK)) {
			return false;
		}
		CurrencyHistoryPK castOther = (CurrencyHistoryPK)other;
		return 
			this.currencyCode.equals(castOther.currencyCode)
			&& this.modifyDate.equals(castOther.modifyDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.currencyCode.hashCode();
		hash = hash * prime + this.modifyDate.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "CurrencyHistoryPK [currencyCode=" + currencyCode + ", modifyDate=" + modifyDate + "]";
	}
	
}