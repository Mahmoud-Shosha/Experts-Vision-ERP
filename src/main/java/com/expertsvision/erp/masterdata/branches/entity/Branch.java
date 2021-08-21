package com.expertsvision.erp.masterdata.branches.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the branches database table.
 * 
 */
@Entity
@Table(name="branches")
@NamedQuery(name="Branch.findAll", query="SELECT b FROM Branch b")
public class Branch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="branch_no")
	private Integer branchNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="branch_d_address")
	private String branchDAddress;

	@Column(name="branch_d_name")
	private String branchDName;

	@Column(name="branch_f_address")
	private String branchFAddress;

	@Column(name="branch_f_name")
	private String branchFName;

	@Column(name="capital")
	private Integer capital;

	@Column(name="city_no")
	private Integer cityNo;

	@Column(name="company_no")
	private Integer companyNo;

	@Column(name="country_no")
	private Integer countryNo;

	@Column(name="cr_no")
	private Integer crNo;

	@Column(name="logo")
	private String logo;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="province_no")
	private Integer provinceNo;

	@Column(name="report_d_header1")
	private String reportDHeader1;

	@Column(name="report_d_header2")
	private String reportDHeader2;

	@Column(name="report_d_header3")
	private String reportDHeader3;

	@Column(name="report_f_header1")
	private String reportFHeader1;

	@Column(name="report_f_header2")
	private String reportFHeader2;

	@Column(name="report_f_header3")
	private String reportFHeader3;

	@Column(name="shortcut_d")
	private String shortcutD;

	@Column(name="shortcut_f")
	private String shortcutF;

	@Column(name="tax_no")
	private Integer taxNo;

	@Column(name="telephone_no")
	private String telephoneNo;

	public Branch() {
	}

	public Integer getBranchNo() {
		return this.branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Integer getAddUser() {
		return this.addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public String getBranchDAddress() {
		return this.branchDAddress;
	}

	public void setBranchDAddress(String branchDAddress) {
		this.branchDAddress = branchDAddress;
	}

	public String getBranchDName() {
		return this.branchDName;
	}

	public void setBranchDName(String branchDName) {
		this.branchDName = branchDName;
	}

	public String getBranchFAddress() {
		return this.branchFAddress;
	}

	public void setBranchFAddress(String branchFAddress) {
		this.branchFAddress = branchFAddress;
	}

	public String getBranchFName() {
		return this.branchFName;
	}

	public void setBranchFName(String branchFName) {
		this.branchFName = branchFName;
	}

	public Integer getCapital() {
		return this.capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public Integer getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public Integer getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public Integer getCountryNo() {
		return this.countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	public Integer getCrNo() {
		return this.crNo;
	}

	public void setCrNo(Integer crNo) {
		this.crNo = crNo;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Integer getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getReportDHeader1() {
		return this.reportDHeader1;
	}

	public void setReportDHeader1(String reportDHeader1) {
		this.reportDHeader1 = reportDHeader1;
	}

	public String getReportDHeader2() {
		return this.reportDHeader2;
	}

	public void setReportDHeader2(String reportDHeader2) {
		this.reportDHeader2 = reportDHeader2;
	}

	public String getReportDHeader3() {
		return this.reportDHeader3;
	}

	public void setReportDHeader3(String reportDHeader3) {
		this.reportDHeader3 = reportDHeader3;
	}

	public String getReportFHeader1() {
		return this.reportFHeader1;
	}

	public void setReportFHeader1(String reportFHeader1) {
		this.reportFHeader1 = reportFHeader1;
	}

	public String getReportFHeader2() {
		return this.reportFHeader2;
	}

	public void setReportFHeader2(String reportFHeader2) {
		this.reportFHeader2 = reportFHeader2;
	}

	public String getReportFHeader3() {
		return this.reportFHeader3;
	}

	public void setReportFHeader3(String reportFHeader3) {
		this.reportFHeader3 = reportFHeader3;
	}

	public String getShortcutD() {
		return this.shortcutD;
	}

	public void setShortcutD(String shortcutD) {
		this.shortcutD = shortcutD;
	}

	public String getShortcutF() {
		return this.shortcutF;
	}

	public void setShortcutF(String shortcutF) {
		this.shortcutF = shortcutF;
	}

	public Integer getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(Integer taxNo) {
		this.taxNo = taxNo;
	}

	public String getTelephoneNo() {
		return this.telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	@Override
	public String toString() {
		return "Branch [branchNo=" + branchNo + ", addDate=" + addDate + ", addUser=" + addUser + ", branchDAddress="
				+ branchDAddress + ", branchDName=" + branchDName + ", branchFAddress=" + branchFAddress
				+ ", branchFName=" + branchFName + ", capital=" + capital + ", cityNo=" + cityNo + ", companyNo="
				+ companyNo + ", countryNo=" + countryNo + ", crNo=" + crNo + ", logo=" + logo
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", provinceNo=" + provinceNo
				+ ", reportDHeader1=" + reportDHeader1 + ", reportDHeader2=" + reportDHeader2 + ", reportDHeader3="
				+ reportDHeader3 + ", reportFHeader1=" + reportFHeader1 + ", reportFHeader2=" + reportFHeader2
				+ ", reportFHeader3=" + reportFHeader3 + ", shortcutD=" + shortcutD + ", shortcutF=" + shortcutF
				+ ", taxNo=" + taxNo + ", telephoneNo=" + telephoneNo + "]";
	}

}