package com.expertsvision.erp.masterdata.cash.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CashInHandViewFilter {

	@JsonProperty("cash_no")
	private Integer cashNo;

	@JsonProperty("acc_no")
	private Integer accNo;

	@JsonProperty("branch_no")
	private Integer branchNo;

	@JsonProperty("cash_d_name")
	private String cashDName;

	@JsonProperty("cash_f_no")
	private String cashFNo;

	@JsonProperty("pos")
	private Boolean pos;

	public Integer getCashNo() {
		return cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public Integer getAccNo() {
		return accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public Integer getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public String getCashDName() {
		return cashDName;
	}

	public void setCashDName(String cashDName) {
		this.cashDName = cashDName;
	}

	public String getCashFNo() {
		return cashFNo;
	}

	public void setCashFNo(String cashFNo) {
		this.cashFNo = cashFNo;
	}

	public Boolean getPos() {
		return pos;
	}

	public void setPos(Boolean pos) {
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "CashInHandViewFilter [cashNo=" + cashNo + ", accNo=" + accNo + ", branchNo=" + branchNo + ", cashDName="
				+ cashDName + ", cashFNo=" + cashFNo + ", pos=" + pos + "]";
	}

}