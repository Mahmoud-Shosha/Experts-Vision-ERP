package com.expertsvision.erp.masterdata.banks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BanksViewFilter {

	@JsonProperty("branch_no")
	private Integer branchNo;

	@JsonProperty("bank_no")
	private Integer bankNo;

	@JsonProperty("account_no")
	private Integer accountNo;

	@JsonProperty("bank_d_name")
	private String bankDName;

	@JsonProperty("bank_f_name")
	private String bankFName;

	public Integer getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public Integer getBankNo() {
		return bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankDName() {
		return bankDName;
	}

	public void setBankDName(String bankDName) {
		this.bankDName = bankDName;
	}

	public String getBankFName() {
		return bankFName;
	}

	public void setBankFName(String bankFName) {
		this.bankFName = bankFName;
	}

	@Override
	public String toString() {
		return "BanksViewFilter [branchNo=" + branchNo + ", bankNo=" + bankNo + ", accountNo=" + accountNo
				+ ", bankDName=" + bankDName + ", bankFName=" + bankFName + "]";
	}

}