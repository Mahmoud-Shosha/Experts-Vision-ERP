package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;

/**
 * The primary key class for the banks_priv database table.
 * 
 */
public class BanksPrivPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String accCurr;

	private Integer bankNo;

	public BanksPrivPK() {
	}

	public Integer getBankNo() {
		return bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BanksPrivPK)) {
			return false;
		}
		BanksPrivPK castOther = (BanksPrivPK) other;
		return this.userId.equals(castOther.userId) && this.accCurr.equals(castOther.accCurr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.accCurr.hashCode();

		return hash;
	}

	@Override
	public String toString() {
		return "BanksPrivPK [userId=" + userId + ", accCurr=" + accCurr + "]";
	}
}