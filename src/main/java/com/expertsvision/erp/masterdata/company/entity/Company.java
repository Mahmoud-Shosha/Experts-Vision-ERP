package com.expertsvision.erp.masterdata.company.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="company_no")
	private Integer companyNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="company_d_name")
	private String companyDName;

	@Column(name="company_f_name")
	private String companyFName;

	@Column(name="company_group")
	private Integer companyGroup;

	@Column(name="company_mail")
	private String companyMail;

	@Column(name="company_website")
	private String companyWebsite;

	@Column(name="country_no")
	private Integer countryNo;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="shortcut_d")
	private String shortcutD;

	@Column(name="shortcut_f")
	private String shortcutF;

	public Company() {
	}

	public Integer getCompanyNo() {
		return this.companyNo;
	}

	public void setCompanyNo(Integer companyNo) {
		this.companyNo = companyNo;
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

	public String getCompanyWebsite() {
		return this.companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public Integer getCountryNo() {
		return this.countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
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
		return "Company [companyNo=" + companyNo + ", addDate=" + addDate + ", addUser=" + addUser + ", companyDName="
				+ companyDName + ", companyFName=" + companyFName + ", companyGroup=" + companyGroup + ", companyMail="
				+ companyMail + ", companyWebsite=" + companyWebsite + ", countryNo=" + countryNo + ", modifyDate="
				+ modifyDate + ", modifyUser=" + modifyUser + ", shortcutD=" + shortcutD + ", shortcutF=" + shortcutF
				+ "]";
	}

}