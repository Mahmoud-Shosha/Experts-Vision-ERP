package com.expertsvision.erp.masterdata.region.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the region_view database table.
 * 
 */
@Entity
@Table(name="region_view")
@NamedQuery(name="RegionView.findAll", query="SELECT r FROM RegionView r")
public class RegionView implements Serializable {
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

	@JsonProperty("region_d_name")
	@Column(name="region_d_name")
	private String regionDName;

	@JsonProperty("region_f_name")
	@Column(name="region_f_name")
	private String regionFName;

	@Id
	@JsonProperty("region_no")
	@Column(name="region_no")
	private Integer regionNo;

	@JsonProperty("shortcut")
	@Column(name="shortcut")
	private String shortcut;

	public RegionView() {
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
		return "RegionView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", modifyUserDName=" + modifyUserDName + ", modifyUserFName=" + modifyUserFName + ", regionDName="
				+ regionDName + ", regionFName=" + regionFName + ", regionNo=" + regionNo + ", shortcut=" + shortcut
				+ "]";
	}

}