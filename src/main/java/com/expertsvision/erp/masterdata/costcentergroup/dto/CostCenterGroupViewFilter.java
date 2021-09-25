package com.expertsvision.erp.masterdata.costcentergroup.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostCenterGroupViewFilter {
	
	@JsonProperty("group_no")
	private Integer groupNo;

	@JsonProperty("add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	private Integer addUser;

	@JsonProperty("group_d_name")
	private String groupDName;

	@JsonProperty("group_f_name")
	private String groupFName;

	
	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Integer getAddUser() {
		return addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public String getGroupDName() {
		return groupDName;
	}

	public void setGroupDName(String groupDName) {
		this.groupDName = groupDName;
	}

	public String getGroupFName() {
		return groupFName;
	}

	public void setGroupFName(String groupFName) {
		this.groupFName = groupFName;
	}

	@Override
	public String toString() {
		return "CostCenterGroupViewFilter [groupNo=" + groupNo + ", addDate=" + addDate + ", addUser=" + addUser
				+ ", groupDName=" + groupDName + ", groupFName=" + groupFName + "]";
	}

}
