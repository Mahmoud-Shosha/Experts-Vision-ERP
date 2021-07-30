package com.expertsvision.erp.masterdata.zone.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the zone_view database view.
 * 
 */
@Entity
@Table(name="zone_view")
@NamedQuery(name="ZoneView.findAll", query="SELECT z FROM ZoneView z")
public class ZoneView implements Serializable {
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

	@JsonProperty("city_no")
	@Column(name="city_no")
	private Integer cityNo;

	@JsonProperty("city_no_d_name")
	@Column(name="city_no_d_name")
	private String cityNoDName;

	@JsonProperty("city_no_f_name")
	@Column(name="city_no_f_name")
	private String cityNoFName;

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
	
	@JsonProperty("province_no")
	@Column(name="province_no")
	private Integer provinceNo;

	@JsonProperty("region_no_d_name")
	@Column(name="region_no_d_name")
	private String regionNoDName;

	@JsonProperty("region_no_f_name")
	@Column(name="region_no_f_name")
	private String regionNoFName;

	@JsonProperty("zone_d_name")
	@Column(name="zone_d_name")
	private String zoneDName;

	@JsonProperty("zone_f_name")
	@Column(name="zone_f_name")
	private String zoneFName;

	@Id
	@JsonProperty("zone_no")
	@Column(name="zone_no")
	private Integer zoneNo;

	public ZoneView() {
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

	public Integer getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityNoDName() {
		return this.cityNoDName;
	}

	public void setCityNoDName(String cityNoDName) {
		this.cityNoDName = cityNoDName;
	}

	public String getCityNoFName() {
		return this.cityNoFName;
	}

	public void setCityNoFName(String cityNoFName) {
		this.cityNoFName = cityNoFName;
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

	public String getZoneDName() {
		return this.zoneDName;
	}

	public void setZoneDName(String zoneDName) {
		this.zoneDName = zoneDName;
	}

	public String getZoneFName() {
		return this.zoneFName;
	}

	public void setZoneFName(String zoneFName) {
		this.zoneFName = zoneFName;
	}

	public Integer getZoneNo() {
		return this.zoneNo;
	}

	public void setZoneNo(Integer zoneNo) {
		this.zoneNo = zoneNo;
	}

	public Integer getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	public Integer getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	@Override
	public String toString() {
		return "ZoneView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", cityNo=" + cityNo + ", cityNoDName=" + cityNoDName
				+ ", cityNoFName=" + cityNoFName + ", countryNoDName=" + countryNoDName + ", countryNoFName="
				+ countryNoFName + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName="
				+ modifyUserDName + ", modifyUserFName=" + modifyUserFName + ", provinceNoDName=" + provinceNoDName
				+ ", provinceNoFName=" + provinceNoFName + ", regionNo=" + regionNo + ", countryNo=" + countryNo
				+ ", provinceNo=" + provinceNo + ", regionNoDName=" + regionNoDName + ", regionNoFName=" + regionNoFName
				+ ", zoneDName=" + zoneDName + ", zoneFName=" + zoneFName + ", zoneNo=" + zoneNo + "]";
	}

}