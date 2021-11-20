package com.expertsvision.erp.masterdata.cash.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchesViewDTO {

	@JsonProperty("branch_d_address")
	private String branchDAddress;

	@JsonProperty("branch_d_name")
	private String branchDName;

	@JsonProperty("branch_f_address")
	private String branchFAddress;

	@JsonProperty("branch_f_name")
	private String branchFName;
	
	@JsonProperty("branch_no")
	private Integer branchNo;

	@JsonProperty("capital")
	private Integer capital;

	@JsonProperty("city_no")
	private Integer cityNo;

	@JsonProperty("company_no")
	private Integer companyNo;

	@JsonProperty("country_no")
	private Integer countryNo;

	@JsonProperty("cr_no")
	private Integer crNo;

	@JsonProperty("logo")
	private String logo;

	@JsonProperty("province_no")
	private Integer provinceNo;

	@JsonProperty("report_d_header1")
	private String reportDHeader1;

	@JsonProperty("report_d_header2")
	private String reportDHeader2;

	@JsonProperty("report_d_header3")
	private String reportDHeader3;

	@JsonProperty("report_f_header1")
	private String reportFHeader1;

	@JsonProperty("report_f_header2")
	private String reportFHeader2;

	@JsonProperty("report_f_header3")
	private String reportFHeader3;

	@JsonProperty("shortcut_d")
	private String shortcutD;

	@JsonProperty("shortcut_f")
	private String shortcutF;
	
	@JsonProperty("tax_no")
	private Integer taxNo;

	@JsonProperty("telephone_no")
	private String telephoneNo;
	

	public String getBranchDAddress() {
		return branchDAddress;
	}

	public void setBranchDAddress(String branchDAddress) {
		this.branchDAddress = branchDAddress;
	}

	public String getBranchDName() {
		return branchDName;
	}

	public void setBranchDName(String branchDName) {
		this.branchDName = branchDName;
	}

	public String getBranchFAddress() {
		return branchFAddress;
	}

	public void setBranchFAddress(String branchFAddress) {
		this.branchFAddress = branchFAddress;
	}

	public String getBranchFName() {
		return branchFName;
	}

	public void setBranchFName(String branchFName) {
		this.branchFName = branchFName;
	}

	public Integer getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public Integer getCapital() {
		return capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public Integer getCityNo() {
		return cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public Integer getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public Integer getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	public Integer getCrNo() {
		return crNo;
	}

	public void setCrNo(Integer crNo) {
		this.crNo = crNo;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getReportDHeader1() {
		return reportDHeader1;
	}

	public void setReportDHeader1(String reportDHeader1) {
		this.reportDHeader1 = reportDHeader1;
	}

	public String getReportDHeader2() {
		return reportDHeader2;
	}

	public void setReportDHeader2(String reportDHeader2) {
		this.reportDHeader2 = reportDHeader2;
	}

	public String getReportDHeader3() {
		return reportDHeader3;
	}

	public void setReportDHeader3(String reportDHeader3) {
		this.reportDHeader3 = reportDHeader3;
	}

	public String getReportFHeader1() {
		return reportFHeader1;
	}

	public void setReportFHeader1(String reportFHeader1) {
		this.reportFHeader1 = reportFHeader1;
	}

	public String getReportFHeader2() {
		return reportFHeader2;
	}

	public void setReportFHeader2(String reportFHeader2) {
		this.reportFHeader2 = reportFHeader2;
	}

	public String getReportFHeader3() {
		return reportFHeader3;
	}

	public void setReportFHeader3(String reportFHeader3) {
		this.reportFHeader3 = reportFHeader3;
	}

	public String getShortcutD() {
		return shortcutD;
	}

	public void setShortcutD(String shortcutD) {
		this.shortcutD = shortcutD;
	}

	public String getShortcutF() {
		return shortcutF;
	}

	public void setShortcutF(String shortcutF) {
		this.shortcutF = shortcutF;
	}

	public Integer getTaxNo() {
		return taxNo;
	}

	public void setTaxNo(Integer taxNo) {
		this.taxNo = taxNo;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	@Override
	public String toString() {
		return "BranchesViewDTO [branchDAddress=" + branchDAddress + ", branchDName=" + branchDName
				+ ", branchFAddress=" + branchFAddress + ", branchFName=" + branchFName + ", branchNo=" + branchNo
				+ ", capital=" + capital + ", cityNo=" + cityNo + ", companyNo=" + companyNo + ", countryNo="
				+ countryNo + ", crNo=" + crNo + ", logo=" + logo + ", provinceNo=" + provinceNo + ", reportDHeader1="
				+ reportDHeader1 + ", reportDHeader2=" + reportDHeader2 + ", reportDHeader3=" + reportDHeader3
				+ ", reportFHeader1=" + reportFHeader1 + ", reportFHeader2=" + reportFHeader2 + ", reportFHeader3="
				+ reportFHeader3 + ", shortcutD=" + shortcutD + ", shortcutF=" + shortcutF + ", taxNo=" + taxNo
				+ ", telephoneNo=" + telephoneNo + "]";
	}	
	
}
