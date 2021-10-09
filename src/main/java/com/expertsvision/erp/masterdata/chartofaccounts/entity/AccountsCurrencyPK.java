package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;

/**
 * The primary key class for the accounts_currency database table.
 * 
 */
public class AccountsCurrencyPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer accNo;

	private String curCode;

	public AccountsCurrencyPK() {
	}
	public Integer getAccNo() {
		return this.accNo;
	}
	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}
	public String getCurCode() {
		return this.curCode;
	}
	public void setCurCode(String curCode) {
		this.curCode = curCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AccountsCurrencyPK)) {
			return false;
		}
		AccountsCurrencyPK castOther = (AccountsCurrencyPK)other;
		return 
			this.accNo.equals(castOther.accNo)
			&& this.curCode.equals(castOther.curCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.accNo.hashCode();
		hash = hash * prime + this.curCode.hashCode();
		
		return hash;
	}
}