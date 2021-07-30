package com.expertsvision.erp.masterdata.region.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the region database table.
 * 
 */
@Entity
@Table(name="region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="region_no")
	private Integer regionNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="region_d_name")
	private String regionDName;

	@Column(name="region_f_name")
	private String regionFName;

	@Column(name="shortcut")
	private String shortcut;

	public Region() {
	}

	public Integer getRegionNo() {
		return this.regionNo;
	}

	public void setRegionNo(Integer regionNo) {
		this.regionNo = regionNo;
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

	public String getRegionDName() {
		return this.regionDName;
	}

	public void setRegionDName(String regionDName) {
		this.regionDName = regionDName;
	}

	public String getRegionFName() {
		return this.regionFName;
	}

	public void setRegionFName(String regionFName) {
		this.regionFName = regionFName;
	}

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "Region [regionNo=" + regionNo + ", addDate=" + addDate + ", addUser=" + addUser + ", modifyDate="
				+ modifyDate + ", modifyUser=" + modifyUser + ", regionDName=" + regionDName + ", regionFName="
				+ regionFName + ", shortcut=" + shortcut + "]";
	}

}