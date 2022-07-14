package com.expertsvision.erp.masterdata.inventory.maingroups.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainGroupViewFilter {

	@JsonProperty("group_code")
	private String groupCode;

	@JsonProperty("group_d_name")
	private String groupDName;

	@JsonProperty("group_f_name")
	private String groupFName;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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
		return "BranchesViewFilter [groupCode=" + groupCode + "]";
	}

}