package com.expertsvision.erp.core.user.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersViewFilter {
	
	@JsonProperty("user_id")
	private Integer userId;
	
	@JsonProperty("user_d_name")
	private String userDName;

	@JsonProperty("user_f_name")
	private String userFName;
	
	@JsonProperty("direct_mang")
	private Integer directMang;

	@JsonProperty("group_no")
	private Integer groupNo;
	
	@JsonProperty("inactive")
	private Boolean inactive;
	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserDName() {
		return userDName;
	}

	public void setUserDName(String userDName) {
		this.userDName = userDName;
	}

	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public Integer getDirectMang() {
		return directMang;
	}

	public void setDirectMang(Integer directMang) {
		this.directMang = directMang;
	}

	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public Boolean getInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}
	

	@Override
	public String toString() {
		return "UsersViewFilter [userId=" + userId + ", userDName=" + userDName + ", userFName=" + userFName
				+ ", directMang=" + directMang + ", groupNo=" + groupNo + ", inactive=" + inactive + "]";
	}

}
