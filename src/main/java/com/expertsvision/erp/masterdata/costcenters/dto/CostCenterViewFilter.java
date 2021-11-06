package com.expertsvision.erp.masterdata.costcenters.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostCenterViewFilter {

	@JsonProperty("cc_no")
	private Integer ccNo;

	@JsonProperty("cc_d_name")
	private String ccDName;

	@JsonProperty("cc_f_name")
	private String ccFName;

	@JsonProperty("cc_group")
	private Integer ccGroup;

	@JsonProperty("parent_cc")
	private Integer parentCc;

	@JsonProperty("sub")
	private Boolean sub;

	public Integer getCcNo() {
		return ccNo;
	}

	public void setCcNo(Integer ccNo) {
		this.ccNo = ccNo;
	}

	public String getCcDName() {
		return ccDName;
	}

	public void setCcDName(String ccDName) {
		this.ccDName = ccDName;
	}

	public String getCcFName() {
		return ccFName;
	}

	public void setCcFName(String ccFName) {
		this.ccFName = ccFName;
	}

	public Integer getCcGroup() {
		return ccGroup;
	}

	public void setCcGroup(Integer ccGroup) {
		this.ccGroup = ccGroup;
	}

	public Integer getParentCc() {
		return parentCc;
	}

	public void setParentCc(Integer parentCc) {
		this.parentCc = parentCc;
	}

	public Boolean getSub() {
		return sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

}