package com.expertsvision.erp.masterdata.branches.dto;


import com.fasterxml.jackson.annotation.JsonProperty;


public class BranchesViewFilter {
	
	@JsonProperty("branch_no")
	private Integer branchNo;

	@JsonProperty("branch_d_name")
	private String branchDName;

	@JsonProperty("branch_f_name")
	private String branchFName;

	@JsonProperty("company_no")
	private Integer companyNo;
	

	public Integer getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public String getBranchDName() {
		return branchDName;
	}

	public void setBranchDName(String branchDName) {
		this.branchDName = branchDName;
	}

	public String getBranchFName() {
		return branchFName;
	}

	public void setBranchFName(String branchFName) {
		this.branchFName = branchFName;
	}

	public Integer getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	@Override
	public String toString() {
		return "BranchesViewFilter [branchNo=" + branchNo + ", branchDName=" + branchDName + ", branchFName="
				+ branchFName + ", companyNo=" + companyNo + "]";
	}
	
}