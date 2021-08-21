package com.expertsvision.erp.masterdata.company.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the company_view database table.
 * 
 */
@Entity
@Table(name="company_view")
@NamedQuery(name="CompanyView.findAll", query="SELECT c FROM CompanyView c")
public class CompanyView implements Serializable {
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

	@JsonProperty("company_d_name")
	@Column(name="company_d_name")
	private String companyDName;

	@JsonProperty("company_f_name")
	@Column(name="company_f_name")
	private String companyFName;

	@JsonProperty("company_group")
	@Column(name="company_group")
	private Integer companyGroup;

	@JsonProperty("company_mail")
	@Column(name="company_mail")
	private String companyMail;

	@Id
	@JsonProperty("company_no")
	@Column(name="company_no")
	private Integer companyNo;

	@JsonProperty("company_website")
	@Column(name="company_website")
	private String companyWebsite;

	@JsonProperty("country_d_name")
	@Column(name="country_d_name")
	private String countryDName;

	@JsonProperty("country_f_name")
	@Column(name="country_f_name")
	private String countryFName;

	@JsonProperty("country_no")
	@Column(name="country_no")
	private Integer countryNo;

	@JsonProperty("group_d_name")
	@Column(name="group_d_name")
	private String groupDName;

	@JsonProperty("group_f_name")
	@Column(name="group_f_name")
	private String groupFName;

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

	@JsonProperty("shortcut_d")
	@Column(name="shortcut_d")
	private String shortcutD;

	@JsonProperty("shortcut_f")
	@Column(name="shortcut_f")
	private String shortcutF;

	public CompanyView() {
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

	public Integer getCompanyGroup() {
		return this.companyGroup;
	}

	public void setCompanyGroup(Integer companyGroup) {
		this.companyGroup = companyGroup;
	}

	public String getCompanyMail() {
		return this.companyMail;
	}

	public void setCompanyMail(String companyMail) {
		this.companyMail = companyMail;
	}

	public Integer getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
	}

	public String getCompanyWebsite() {
		return this.companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
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

	public String getGroupDName() {
		return this.groupDName;
	}

	public void setGroupDName(String groupDName) {
		this.groupDName = groupDName;
	}

	public String getGroupFName() {
		return this.groupFName;
	}

	public void setGroupFName(String groupFName) {
		this.groupFName = groupFName;
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

	@Override
	public String toString() {
		return "CompanyView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", companyDName=" + companyDName + ", companyFName=" + companyFName
				+ ", companyGroup=" + companyGroup + ", companyMail=" + companyMail + ", companyNo=" + companyNo
				+ ", companyWebsite=" + companyWebsite + ", countryDName=" + countryDName + ", countryFName="
				+ countryFName + ", countryNo=" + countryNo + ", groupDName=" + groupDName + ", groupFName="
				+ groupFName + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName="
				+ modifyUserDName + ", modifyUserFName=" + modifyUserFName + ", shortcutD=" + shortcutD + ", shortcutF="
				+ shortcutF + "]";
	}

}