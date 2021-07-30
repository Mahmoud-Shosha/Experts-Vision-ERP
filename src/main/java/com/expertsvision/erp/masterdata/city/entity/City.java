package com.expertsvision.erp.masterdata.city.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_no")
	private Integer cityNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="city_d_name")
	private String cityDName;

	@Column(name="city_f_name")
	private String cityFName;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="province_no")
	private Integer provinceNo;

	@Column(name="shortcut")
	private String shortcut;

	public City() {
	}

	public Integer getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
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

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "City [cityNo=" + cityNo + ", addDate=" + addDate + ", addUser=" + addUser + ", cityDName=" + cityDName
				+ ", cityFName=" + cityFName + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", provinceNo=" + provinceNo + ", shortcut=" + shortcut + "]";
	}

}