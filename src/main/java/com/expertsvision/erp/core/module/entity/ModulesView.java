package com.expertsvision.erp.core.module.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the modules_view database view.
 * 
 */
@Entity
@Table(name="modules_view")
@NamedQuery(name="ModulesView.findAll", query="SELECT m FROM ModulesView m")
public class ModulesView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("active")
	@Column(name="active")
	private Boolean active;

	@JsonProperty("module_d_name")
	@Column(name="module_d_name")
	private String moduleDName;

	@JsonProperty("module_f_name")
	@Column(name="module_f_name")
	private String moduleFName;

	@Id
	@JsonProperty("module_no")
	@Column(name="module_no")
	private Integer moduleNo;

	@JsonProperty("order_no")
	@Column(name="order_no")
	private Integer orderNo;

	@JsonProperty("shortcut")
	@Column(name="shortcut")
	private String shortcut;

	public ModulesView() {
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getModuleDName() {
		return this.moduleDName;
	}

	public void setModuleDName(String moduleDName) {
		this.moduleDName = moduleDName;
	}

	public String getModuleFName() {
		return this.moduleFName;
	}

	public void setModuleFName(String moduleFName) {
		this.moduleFName = moduleFName;
	}

	public Integer getModuleNo() {
		return this.moduleNo;
	}

	public void setModuleNo(Integer moduleNo) {
		this.moduleNo = moduleNo;
	}

	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public String getShortcut() {
		return this.shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "ModulesView [active=" + active + ", moduleDName=" + moduleDName + ", moduleFName=" + moduleFName
				+ ", moduleNo=" + moduleNo + ", orderNo=" + orderNo + ", shortcut=" + shortcut + "]";
	}

}