package com.expertsvision.erp.masterdata.costcentergroup.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cost_center_group database table.
 * 
 */
@Entity
@Table(name="cost_center_group")
@NamedQuery(name="CostCenterGroup.findAll", query="SELECT c FROM CostCenterGroup c")
public class CostCenterGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="group_no")
	private Integer groupNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="group_d_name")
	private String groupDName;

	@Column(name="group_f_name")
	private String groupFName;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	public CostCenterGroup() {
	}

	public Integer getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
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

	public String getGroupDName() {
		return this.groupDName;
	}

	public void setGroupDName(String groupDName) {
		this.groupDName = groupDName;
	}

	public String getGroupFName() {
		return this.groupFName;
	}

	public void setGroupFName(String groupFName) {
		this.groupFName = groupFName;
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

	@Override
	public String toString() {
		return "CostCenterGroup [groupNo=" + groupNo + ", addDate=" + addDate + ", addUser=" + addUser + ", groupDName="
				+ groupDName + ", groupFName=" + groupFName + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + "]";
	}

}