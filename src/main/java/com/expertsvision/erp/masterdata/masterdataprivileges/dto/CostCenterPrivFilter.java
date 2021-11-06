package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostCenterPrivFilter {

	@JsonProperty("from_cc_no")
	private Integer fromCcNo;

	@JsonProperty("to_cc_no")
	private Integer toCcNo;

	@JsonProperty("from_group_no")
	private Integer fromGroupNo;

	@JsonProperty("to_group_no")
	private Integer toGroupNo;

	@JsonProperty("from_user_id")
	private Integer fromUserId;

	@JsonProperty("to_user_id")
	private Integer toUserId;

	@JsonProperty("group_no")
	private Integer groupNo;

	public Integer getFromCcNo() {
		return fromCcNo;
	}

	public void setFromCcNo(Integer fromCcNo) {
		this.fromCcNo = fromCcNo;
	}

	public Integer getToCcNo() {
		return toCcNo;
	}

	public void setToCcNo(Integer toCcNo) {
		this.toCcNo = toCcNo;
	}

	public Integer getFromGroupNo() {
		return fromGroupNo;
	}

	public void setFromGroupNo(Integer fromGroupNo) {
		this.fromGroupNo = fromGroupNo;
	}

	public Integer getToGroupNo() {
		return toGroupNo;
	}

	public void setToGroupNo(Integer toGroupNo) {
		this.toGroupNo = toGroupNo;
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
