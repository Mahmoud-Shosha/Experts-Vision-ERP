package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CashesPrivFilter {

	@JsonProperty("from_cash_no")
	private Integer FromCashNo;

	@JsonProperty("to_cash_no")
	private Integer toCashNo;

	@JsonProperty("from_user_id")
	private Integer fromUserId;

	@JsonProperty("to_user_id")
	private Integer toUserId;

	@JsonProperty("group_no")
	private Integer groupNo;

	public Integer getFromCashNo() {
		return FromCashNo;
	}

	public void setFromCashNo(Integer fromCashNo) {
		FromCashNo = fromCashNo;
	}

	public Integer getToCashNo() {
		return toCashNo;
	}

	public void setToCashNo(Integer toCashNo) {
		this.toCashNo = toCashNo;
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
