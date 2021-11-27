package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;

/**
 * The primary key class for the cash_in_hand_dtl database table.
 * 
 */
public class CashInHandDtlPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer cashNo;

	private String accCurr;

	private Integer accNo;

	public CashInHandDtlPK() {
	}

	public Integer getCashNo() {
		return this.cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public String getAccCurr() {
		return this.accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
	}

	public Integer getAccNo() {
		return accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CashInHandDtlPK)) {
			return false;
		}
		CashInHandDtlPK castOther = (CashInHandDtlPK) other;
		return this.cashNo.equals(castOther.cashNo) && this.accCurr.equals(castOther.accCurr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cashNo.hashCode();
		hash = hash * prime + this.accCurr.hashCode();

		return hash;
	}
}