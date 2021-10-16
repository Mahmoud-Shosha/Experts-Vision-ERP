package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountsPrivFilter {
	
	@JsonProperty("from_account_no")
	private Integer fromAccountNo;
	
	@JsonProperty("to_account_no")
	private Integer toAccountNo;

	@JsonProperty("from_group_no")
	private Integer fromGroupNo;
	
	@JsonProperty("to_group_no")
	private Integer toGroupNo;
	
	@JsonProperty("currency_list")
	private List<String> currencyList;
	
	@JsonProperty("from_user_id")
	private Integer fromUserId;
	
	@JsonProperty("to_user_id")
	private Integer toUserId;
	
	@JsonProperty("group_no")
	private Integer groupNo;

	public Integer getFromAccountNo() {
		return fromAccountNo;
	}

	public void setFromAccountNo(Integer fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}

	public Integer getToAccountNo() {
		return toAccountNo;
	}

	public void setToAccountNo(Integer toAccountNo) {
		this.toAccountNo = toAccountNo;
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

	public List<String> getCurrencyList() {
		return currencyList;
	}

	public void setCurrencyList(List<String> currencyList) {
		this.currencyList = currencyList;
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
