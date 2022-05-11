package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BanksPrivFilter {

	@JsonProperty("from_bank_no")
	private Integer FromBankNo;

	@JsonProperty("to_bank_no")
	private Integer toBankNo;

	@JsonProperty("from_user_id")
	private Integer fromUserId;

	@JsonProperty("to_user_id")
	private Integer toUserId;

	@JsonProperty("group_no")
	private Integer groupNo;

	public Integer getFromBankNo() {
		return FromBankNo;
	}

	public void setFromBankNo(Integer fromBankNo) {
		FromBankNo = fromBankNo;
	}

	public Integer getToBankNo() {
		return toBankNo;
	}

	public void setToBankNo(Integer toBankNo) {
		this.toBankNo = toBankNo;
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
