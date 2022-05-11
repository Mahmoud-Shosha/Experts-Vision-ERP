package com.expertsvision.erp.masterdata.banks.dto;

import java.util.Objects;

public class BankVirtualPK {

	private Integer bankNo;
	private String accCurr;

	public Integer getBankNo() {
		return bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public String getAccCurr() {
		return accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accCurr, bankNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankVirtualPK other = (BankVirtualPK) obj;
		return Objects.equals(accCurr, other.accCurr) && Objects.equals(bankNo, other.bankNo);
	}

}
