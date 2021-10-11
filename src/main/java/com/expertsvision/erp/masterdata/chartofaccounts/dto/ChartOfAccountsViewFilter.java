package com.expertsvision.erp.masterdata.chartofaccounts.dto;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ChartOfAccountsViewFilter {

	@JsonProperty("acc_no")
	private Integer accNo;

	@JsonProperty("acc_d_name")
	private String accDName;

	@JsonProperty("acc_f_name")
	private String accFName;

	@JsonProperty("sub")
	private Boolean sub;

	@JsonProperty("parent_acc")
	private Integer parentAcc;
	
	@JsonProperty("bs")
	private Boolean bs;

	public Integer getAccNo() {
		return accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public String getAccDName() {
		return accDName;
	}

	public void setAccDName(String accDName) {
		this.accDName = accDName;
	}

	public String getAccFName() {
		return accFName;
	}

	public void setAccFName(String accFName) {
		this.accFName = accFName;
	}

	public Boolean getSub() {
		return sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

	public Integer getParentAcc() {
		return parentAcc;
	}

	public void setParentAcc(Integer parentAcc) {
		this.parentAcc = parentAcc;
	}

	public Boolean getBs() {
		return bs;
	}

	public void setBs(Boolean bs) {
		this.bs = bs;
	}

	@Override
	public String toString() {
		return "ChartOfAccountsViewFilter [accNo=" + accNo + ", accDName=" + accDName + ", accFName=" + accFName
				+ ", sub=" + sub + ", parentAcc=" + parentAcc + ", bs=" + bs + "]";
	}
	
}