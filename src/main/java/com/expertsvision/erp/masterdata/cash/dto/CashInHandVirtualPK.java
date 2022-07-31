package com.expertsvision.erp.masterdata.cash.dto;

import java.util.Objects;

public class CashInHandVirtualPK {

	private Integer cashNo;
	private String accCurr;

	public CashInHandVirtualPK(Integer cashNo, String accCurr) {
		super();
		this.cashNo = cashNo;
		this.accCurr = accCurr;
	}

	public Integer getCashNo() {
		return cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public String getAccCurr() {
		return accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accCurr, cashNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashInHandVirtualPK other = (CashInHandVirtualPK) obj;
		return Objects.equals(accCurr, other.accCurr) && Objects.equals(cashNo, other.cashNo);
	}

}
