package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the banks_dtl database table.
 * 
 */
@Embeddable
public class BanksDtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="bank_no", insertable=false, updatable=false)
	private Integer bankNo;

	@Column(name="acc_no", insertable=false, updatable=false)
	private Integer accNo;

	public BanksDtlPK() {
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

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BanksDtlPK)) {
			return false;
		}
		BanksDtlPK castOther = (BanksDtlPK)other;
		return 
			this.bankNo.equals(castOther.bankNo)
			&& this.accNo.equals(castOther.accNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.bankNo.hashCode();
		hash = hash * prime + this.accNo.hashCode();
		
		return hash;
	}
}