package com.expertsvision.erp.masterdata.country.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@Table(name="country")
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_no")
	private Integer countryNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="country_d_name")
	private String countryDName;

	@Column(name="country_f_name")
	private String countryFName;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="region_no")
	private Integer regionNo;

	@Column(name="shortcut")
	private String shortcut;

	public Country() {
	}

	public Integer getCountryNo() {
		return this.countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
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

	public Integer getRegionNo() {
		return this.regionNo;
	}

	public void setRegionNo(Integer regionNo) {
		this.regionNo = regionNo;
	}

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "Country [countryNo=" + countryNo + ", addDate=" + addDate + ", addUser=" + addUser + ", countryDName="
				+ countryDName + ", countryFName=" + countryFName + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + ", regionNo=" + regionNo + ", shortcut=" + shortcut + "]";
	}

}