package com.expertsvision.erp.masterdata.province.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the province database table.
 * 
 */
@Entity
@Table(name="province")
@NamedQuery(name="Province.findAll", query="SELECT p FROM Province p")
public class Province implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="province_no")
	private Integer provinceNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="country_no")
	private Integer countryNo;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="province_d_name")
	private String provinceDName;

	@Column(name="province_f_name")
	private String provinceFName;

	@Column(name="shortcut")
	private String shortcut;

	public Province() {
	}

	public Integer getProvinceNo() {
		return this.provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
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

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "Province [provinceNo=" + provinceNo + ", addDate=" + addDate + ", addUser=" + addUser + ", countryNo="
				+ countryNo + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", provinceDName="
				+ provinceDName + ", provinceFName=" + provinceFName + ", shortcut=" + shortcut + "]";
	}

}