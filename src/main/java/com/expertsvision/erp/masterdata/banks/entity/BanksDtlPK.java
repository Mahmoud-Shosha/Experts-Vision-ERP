package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The primary key class for the banks_dtl database table.
 * 
 */
public class BanksDtlPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer bankNo;

	private Integer accNo;

	private String accCurr;

	public BanksDtlPK() {
	}

	public BanksDtlPK(Integer bankNo, Integer accNo, String accCurr) {
		super();
		this.bankNo = bankNo;
		this.accNo = accNo;
		this.accCurr = accCurr;
	}

	public Integer getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getAccNo() {
		return this.accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public String getAccCurr() {
		return accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accCurr, accNo, bankNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BanksDtlPK other = (BanksDtlPK) obj;
		return Objects.equals(accCurr, other.accCurr) && Objects.equals(accNo, other.accNo)
				&& Objects.equals(bankNo, other.bankNo);
	}

}