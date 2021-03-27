package com.expertsvision.erp.core.form.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormsViewFilter {
	
	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("form_d_name")
	private String formDName;

	@JsonProperty("form_f_name")
	private String formFName;

	@JsonProperty("form_no")
	private Integer formNo;

	@JsonProperty("main")
	private Boolean main;

	@JsonProperty("module_no")
	private Integer moduleNo;
	

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFormDName() {
		return formDName;
	}

	public void setFormDName(String formDName) {
		this.formDName = formDName;
	}

	public String getFormFName() {
		return formFName;
	}

	public void setFormFName(String formFName) {
		this.formFName = formFName;
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public Integer getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(Integer moduleNo) {
		this.moduleNo = moduleNo;
	}
	

	@Override
	public String toString() {
		return "FormsViewFilter [active=" + active + ", formDName=" + formDName + ", formFName=" + formFName
				+ ", formNo=" + formNo + ", main=" + main + ", moduleNo=" + moduleNo + "]";
	}

}
