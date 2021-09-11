package com.expertsvision.erp.masterdata.branches.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the branches_view database table.
 * 
 */
@Entity
@Table(name="branches_view")
@NamedQuery(name="BranchesView.findAll", query="SELECT b FROM BranchesView b")
public class BranchesView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name="add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name="add_user_f_name")
	private String addUserFName;

	@JsonProperty("branch_d_address")
	@Column(name="branch_d_address")
	private String branchDAddress;

	@JsonProperty("branch_d_name")
	@Column(name="branch_d_name")
	private String branchDName;

	@JsonProperty("branch_f_address")
	@Column(name="branch_f_address")
	private String branchFAddress;

	@JsonProperty("branch_f_name")
	@Column(name="branch_f_name")
	private String branchFName;
	
	@Id
	@JsonProperty("branch_no")
	@Column(name="branch_no")
	private Integer branchNo;

	@JsonProperty("capital")
	@Column(name="capital")
	private Integer capital;

	@JsonProperty("city_d_name")
	@Column(name="city_d_name")
	private String cityDName;

	@JsonProperty("city_f_name")
	@Column(name="city_f_name")
	private String cityFName;

	@JsonProperty("city_no")
	@Column(name="city_no")
	private Integer cityNo;

	@JsonProperty("company_d_name")
	@Column(name="company_d_name")
	private String companyDName;

	@JsonProperty("company_f_name")
	@Column(name="company_f_name")
	private String companyFName;

	@JsonProperty("company_no")
	@Column(name="company_no")
	private Integer companyNo;

	@JsonProperty("country_d_name")
	@Column(name="country_d_name")
	private String countryDName;

	@JsonProperty("country_f_name")
	@Column(name="country_f_name")
	private String countryFName;

	@JsonProperty("country_no")
	@Column(name="country_no")
	private Integer countryNo;

	@JsonProperty("cr_no")
	@Column(name="cr_no")
	private Integer crNo;

	@JsonProperty("logo")
	@Column(name="logo")
	private String logo;

	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	@JsonProperty("province_d_name")
	@Column(name="province_d_name")
	private String provinceDName;

	@JsonProperty("province_f_name")
	@Column(name="province_f_name")
	private String provinceFName;

	@JsonProperty("province_no")
	@Column(name="province_no")
	private Integer provinceNo;

	@JsonProperty("report_d_header1")
	@Column(name="report_d_header1")
	private String reportDHeader1;

	@JsonProperty("report_d_header2")
	@Column(name="report_d_header2")
	private String reportDHeader2;

	@JsonProperty("report_d_header3")
	@Column(name="report_d_header3")
	private String reportDHeader3;

	@JsonProperty("report_f_header1")
	@Column(name="report_f_header1")
	private String reportFHeader1;

	@JsonProperty("report_f_header2")
	@Column(name="report_f_header2")
	private String reportFHeader2;

	@JsonProperty("report_f_header3")
	@Column(name="report_f_header3")
	private String reportFHeader3;

	@JsonProperty("shortcut_d")
	@Column(name="shortcut_d")
	private String shortcutD;

	@JsonProperty("shortcut_f")
	@Column(name="shortcut_f")
	private String shortcutF;
	
	@JsonProperty("tax_no")
	@Column(name="tax_no")
	private Integer taxNo;

	@JsonProperty("telephone_no")
	@Column(name="telephone_no")
	private String telephoneNo;

	public BranchesView() {
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

	public String getAddUserDName() {
		return this.addUserDName;
	}

	public void setAddUserDName(String addUserDName) {
		this.addUserDName = addUserDName;
	}

	public String getAddUserFName() {
		return this.addUserFName;
	}

	public void setAddUserFName(String addUserFName) {
		this.addUserFName = addUserFName;
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

	public Integer getBranchNo() {
		return this.branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public Integer getCapital() {
		return this.capital;
	}

	public void setCapital(Integer capital) {
		this.capital = capital;
	}

	public String getCityDName() {
		return this.cityDName;
	}

	public void setCityDName(String cityDName) {
		this.cityDName = cityDName;
	}

	public String getCityFName() {
		return this.cityFName;
	}

	public void setCityFName(String cityFName) {
		this.cityFName = cityFName;
	}

	public Integer getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public String getCompanyDName() {
		return this.companyDName;
	}

	public void setCompanyDName(String companyDName) {
		this.companyDName = companyDName;
	}

	public String getCompanyFName() {
		return this.companyFName;
	}

	public void setCompanyFName(String companyFName) {
		this.companyFName = companyFName;
	}

	public Integer getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public String getCountryDName() {
		return this.countryDName;
	}

	public void setCountryDName(String countryDName) {
		this.countryDName = countryDName;
	}

	public String getCountryFName() {
		return this.countryFName;
	}

	public void setCountryFName(String countryFName) {
		this.countryFName = countryFName;
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

	public String getModifyUserDName() {
		return this.modifyUserDName;
	}

	public void setModifyUserDName(String modifyUserDName) {
		this.modifyUserDName = modifyUserDName;
	}

	public String getModifyUserFName() {
		return this.modifyUserFName;
	}

	public void setModifyUserFName(String modifyUserFName) {
		this.modifyUserFName = modifyUserFName;
	}

	public String getProvinceDName() {
		return this.provinceDName;
	}

	public void setProvinceDName(String provinceDName) {
		this.provinceDName = provinceDName;
	}

	public String getProvinceFName() {
		return this.provinceFName;
	}

	public void setProvinceFName(String provinceFName) {
		this.provinceFName = provinceFName;
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
	public int hashCode() {
		return Objects.hash(branchNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BranchesView other = (BranchesView) obj;
		return Objects.equals(branchNo, other.branchNo);
	}

	@Override
	public String toString() {
		return "BranchesView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", branchDAddress=" + branchDAddress + ", branchDName="
				+ branchDName + ", branchFAddress=" + branchFAddress + ", branchFName=" + branchFName + ", branchNo="
				+ branchNo + ", capital=" + capital + ", cityDName=" + cityDName + ", cityFName=" + cityFName
				+ ", cityNo=" + cityNo + ", companyDName=" + companyDName + ", companyFName=" + companyFName
				+ ", companyNo=" + companyNo + ", countryDName=" + countryDName + ", countryFName=" + countryFName
				+ ", countryNo=" + countryNo + ", crNo=" + crNo + ", logo=" + logo + ", modifyDate="
				+ modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName
				+ ", modifyUserFName=" + modifyUserFName + ", provinceDName=" + provinceDName + ", provinceFName="
				+ provinceFName + ", provinceNo=" + provinceNo + ", reportDHeader1=" + reportDHeader1
				+ ", reportDHeader2=" + reportDHeader2 + ", reportDHeader3=" + reportDHeader3 + ", reportFHeader1="
				+ reportFHeader1 + ", reportFHeader2=" + reportFHeader2 + ", reportFHeader3=" + reportFHeader3
				+ ", shortcutD=" + shortcutD + ", shortcutF=" + shortcutF + ", taxNo=" + taxNo + ", telephoneNo="
				+ telephoneNo + "]";
	}

}