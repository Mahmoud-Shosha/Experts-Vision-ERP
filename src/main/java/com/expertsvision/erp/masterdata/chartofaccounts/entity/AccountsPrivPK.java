package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;

/**
 * The primary key class for the accounts_priv database table.
 * 
 */
public class AccountsPrivPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer accNo;

	private String accCurr;

	public AccountsPrivPK() {
	}
	
	public AccountsPrivPK(Integer userId, Integer accNo, String accCurr) {
		super();
		this.userId = userId;
		this.accNo = accNo;
		this.accCurr = accCurr;
	}

	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAccNo() {
		return this.accNo;
	}
	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
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
		if (!(other instanceof AccountsPrivPK)) {
			return false;
		}
		AccountsPrivPK castOther = (AccountsPrivPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.accNo.equals(castOther.accNo)
			&& this.accCurr.equals(castOther.accCurr);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.accNo.hashCode();
		hash = hash * prime + this.accCurr.hashCode();
		
		return hash;
	}
}