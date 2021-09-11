package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BranchesPrivFilter {

	@JsonProperty("from_branch_no")
	private Integer FromBranchNo;
	
	@JsonProperty("to_branch_no")
	private Integer toBranchNo;
	
	@JsonProperty("from_user_id")
	private Integer fromUserId;
	
	@JsonProperty("to_user_id")
	private Integer toUserId;
	
	@JsonProperty("group_no")
	private Integer groupNo;

	public Integer getFromBranchNo() {
		return FromBranchNo;
	}

	public void setFromBranchNo(Integer fromBranchNo) {
		FromBranchNo = fromBranchNo;
	}

	public Integer getToBranchNo() {
		return toBranchNo;
	}

	public void setToBranchNo(Integer toBranchNo) {
		this.toBranchNo = toBranchNo;
	}

	public Integer getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Integer fromUserId) {
		this.fromUserId = fromUserId;
	}

	public Integer getToUserId() {
		return toUserId;
	}

	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}
	
}
