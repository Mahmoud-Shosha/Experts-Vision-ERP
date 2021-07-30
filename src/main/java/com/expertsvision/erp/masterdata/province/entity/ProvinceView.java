package com.expertsvision.erp.masterdata.province.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the province_view database view.
 * 
 */
@Entity
@Table(name="province_view")
@NamedQuery(name="ProvinceView.findAll", query="SELECT p FROM ProvinceView p")
public class ProvinceView implements Serializable {
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

	@JsonProperty("country_no")
	@Column(name="country_no")
	private Integer countryNo;

	@JsonProperty("country_no_d_name")
	@Column(name="country_no_d_name")
	private String countryNoDName;

	@JsonProperty("country_no_f_name")
	@Column(name="country_no_f_name")
	private String countryNoFName;

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

	@Id
	@JsonProperty("province_no")
	@Column(name="province_no")
	private Integer provinceNo;

	@JsonProperty("region_no")
	@Column(name="region_no")
	private Integer regionNo;

	@JsonProperty("region_no_d_name")
	@Column(name="region_no_d_name")
	private String regionNoDName;

	@JsonProperty("region_no_f_name")
	@Column(name="region_no_f_name")
	private String regionNoFName;

	@JsonProperty("shortcut")
	@Column(name="shortcut")
	private String shortcut;

	public ProvinceView() {
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

	public Integer getCountryNo() {
		return this.countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	public String getCountryNoDName() {
		return this.countryNoDName;
	}

	public void setCountryNoDName(String countryNoDName) {
		this.countryNoDName = countryNoDName;
	}

	public String getCountryNoFName() {
		return this.countryNoFName;
	}

	public void setCountryNoFName(String countryNoFName) {
		this.countryNoFName = countryNoFName;
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

	public Integer getRegionNo() {
		return this.regionNo;
	}

	public void setRegionNo(Integer regionNo) {
		this.regionNo = regionNo;
	}

	public String getRegionNoDName() {
		return this.regionNoDName;
	}

	public void setRegionNoDName(String regionNoDName) {
		this.regionNoDName = regionNoDName;
	}

	public String getRegionNoFName() {
		return this.regionNoFName;
	}

	public void setRegionNoFName(String regionNoFName) {
		this.regionNoFName = regionNoFName;
	}

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "ProvinceView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", countryNo=" + countryNo + ", countryNoDName=" + countryNoDName
				+ ", countryNoFName=" + countryNoFName + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", modifyUserDName=" + modifyUserDName + ", modifyUserFName=" + modifyUserFName + ", provinceDName="
				+ provinceDName + ", provinceFName=" + provinceFName + ", provinceNo=" + provinceNo + ", regionNo="
				+ regionNo + ", regionNoDName=" + regionNoDName + ", regionNoFName=" + regionNoFName + ", shortcut="
				+ shortcut + "]";
	}

}