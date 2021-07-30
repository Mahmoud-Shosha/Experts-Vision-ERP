package com.expertsvision.erp.masterdata.city.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the city_view database view.
 * 
 */
@Entity
@Table(name="city_view")
@NamedQuery(name="CityView.findAll", query="SELECT c FROM CityView c")
public class CityView implements Serializable {
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

	@JsonProperty("city_d_name")
	@Column(name="city_d_name")
	private String cityDName;

	@JsonProperty("city_f_name")
	@Column(name="city_f_name")
	private String cityFName;

	@Id
	@JsonProperty("city_no")
	@Column(name="city_no")
	private Integer cityNo;

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

	@JsonProperty("province_no")
	@Column(name="province_no")
	private Integer provinceNo;

	@JsonProperty("province_no_d_name")
	@Column(name="province_no_d_name")
	private String provinceNoDName;

	@JsonProperty("province_no_f_name")
	@Column(name="province_no_f_name")
	private String provinceNoFName;

	@JsonProperty("region_no")
	@Column(name="region_no")
	private Integer regionNo;
	
	@JsonProperty("country_no")
	@Column(name="country_no")
	private Integer countryNo;

	@JsonProperty("region_no_d_name")
	@Column(name="region_no_d_name")
	private String regionNoDName;

	@JsonProperty("region_no_f_name")
	@Column(name="region_no_f_name")
	private String regionNoFName;

	@JsonProperty("shortcut")
	@Column(name="shortcut")
	private String shortcut;

	public CityView() {
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

	public Integer getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getProvinceNoDName() {
		return this.provinceNoDName;
	}

	public void setProvinceNoDName(String provinceNoDName) {
		this.provinceNoDName = provinceNoDName;
	}

	public String getProvinceNoFName() {
		return this.provinceNoFName;
	}

	public void setProvinceNoFName(String provinceNoFName) {
		this.provinceNoFName = provinceNoFName;
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

	public Integer getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	@Override
	public String toString() {
		return "CityView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", cityDName=" + cityDName + ", cityFName=" + cityFName
				+ ", cityNo=" + cityNo + ", countryNoDName=" + countryNoDName + ", countryNoFName=" + countryNoFName
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName
				+ ", modifyUserFName=" + modifyUserFName + ", provinceNo=" + provinceNo + ", provinceNoDName="
				+ provinceNoDName + ", provinceNoFName=" + provinceNoFName + ", regionNo=" + regionNo + ", countryNo="
				+ countryNo + ", regionNoDName=" + regionNoDName + ", regionNoFName=" + regionNoFName + ", shortcut="
				+ shortcut + "]";
	}

}