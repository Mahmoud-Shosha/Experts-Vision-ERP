package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;

/**
 * The primary key class for the cash_in_hand_priv database table.
 * 
 */
public class CashInHandPrivPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String accCurr;

	private Integer cashNo;

	public CashInHandPrivPK() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccCurr() {
		return this.accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
	}

	public Integer getCashNo() {
		return cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CashInHandPrivPK)) {
			return false;
		}
		CashInHandPrivPK castOther = (CashInHandPrivPK) other;
		return this.userId.equals(castOther.userId) && this.accCurr.equals(castOther.accCurr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.accCurr.hashCode();

		return hash;
	}
}