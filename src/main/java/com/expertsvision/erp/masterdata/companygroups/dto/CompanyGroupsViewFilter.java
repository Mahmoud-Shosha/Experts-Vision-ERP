package com.expertsvision.erp.masterdata.companygroups.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

public class CompanyGroupsViewFilter {
	
	@JsonProperty("group_no")
	private Integer groupNo;

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
		return "CompanyGroupsViewFilter [groupNo=" + groupNo + ", groupDName=" + groupDName + ", groupFName="
				+ groupFName + "]";
	}
	
}
