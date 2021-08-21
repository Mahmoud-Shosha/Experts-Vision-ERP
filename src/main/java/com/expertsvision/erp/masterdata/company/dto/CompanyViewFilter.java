package com.expertsvision.erp.masterdata.company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyViewFilter {
	
	@JsonProperty("company_no")
	private Integer companyNo;

	@JsonProperty("company_d_name")
	private String companyDName;

	@JsonProperty("company_f_name")
	private String companyFName;

	@JsonProperty("company_group")
	private Integer companyGroup;
	

	public Integer getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyDName() {
		return companyDName;
	}

	public void setCompanyDName(String companyDName) {
		this.companyDName = companyDName;
	}

	public String getCompanyFName() {
		return companyFName;
	}

	public void setCompanyFName(String companyFName) {
		this.companyFName = companyFName;
	}

	public Integer getCompanyGroup() {
		return companyGroup;
	}

	public void setCompanyGroup(Integer companyGroup) {
		this.companyGroup = companyGroup;
	}

	@Override
	public String toString() {
		return "CompanyViewFilter [companyNo=" + companyNo + ", companyDName=" + companyDName + ", companyFName="
				+ companyFName + ", companyGroup=" + companyGroup + "]";
	}

}
