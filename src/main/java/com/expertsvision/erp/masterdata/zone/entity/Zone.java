package com.expertsvision.erp.masterdata.zone.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the zone database table.
 * 
 */
@Entity
@Table(name="zone")
@NamedQuery(name="Zone.findAll", query="SELECT z FROM Zone z")
public class Zone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="zone_no")
	private Integer zoneNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="city_no")
	private Integer cityNo;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="zone_d_name")
	private String zoneDName;

	@Column(name="zone_f_name")
	private String zoneFName;

	public Zone() {
	}

	public Integer getZoneNo() {
		return this.zoneNo;
	}

	public void setZoneNo(Integer zoneNo) {
		this.zoneNo = zoneNo;
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

	public Integer getCityNo() {
		return this.cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
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

	@Override
	public String toString() {
		return "Zone [zoneNo=" + zoneNo + ", addDate=" + addDate + ", addUser=" + addUser + ", cityNo=" + cityNo
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", zoneDName=" + zoneDName
				+ ", zoneFName=" + zoneFName + "]";
	}

}