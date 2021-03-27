package com.expertsvision.erp.core.module.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ModulesViewFilter {
	
	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("module_d_name")
	private String moduleDName;

	@JsonProperty("module_f_name")
	private String moduleFName;

	@JsonProperty("module_no")
	private Integer moduleNo;

	@JsonProperty("order_no")
	private Integer orderNo;

	@JsonProperty("shortcut")
	private String shortcut;
	

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getModuleDName() {
		return moduleDName;
	}

	public void setModuleDName(String moduleDName) {
		this.moduleDName = moduleDName;
	}

	public String getModuleFName() {
		return moduleFName;
	}

	public void setModuleFName(String moduleFName) {
		this.moduleFName = moduleFName;
	}

	public Integer getModuleNo() {
		return moduleNo;
	}

	public void setModuleNo(Integer moduleNo) {
		this.moduleNo = moduleNo;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	
	@Override
	public String toString() {
		return "ModulesViewFilter [active=" + active + ", moduleDName=" + moduleDName + ", moduleFName=" + moduleFName
				+ ", moduleNo=" + moduleNo + ", orderNo=" + orderNo + ", shortcut=" + shortcut + "]";
	}

}
