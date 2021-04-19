package com.expertsvision.erp.core.module.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the modules database table.
 * 
 */
@Entity
@Table(name="modules")
@NamedQuery(name="Module.findAll", query="SELECT m FROM Module m")
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="module_no")
	private Integer moduleNo;

	@Column(name="active")
	private Boolean active;

	@Column(name="module_d_name")
	private String moduleDName;

	@Column(name="module_f_name")
	private String moduleFName;

	@Column(name="order_no")
	private Integer orderNo;

	@Column(name="shortcut")
	private String shortcut;

	public Module() {
	}

	public Integer getModuleNo() {
		return this.moduleNo;
	}

	public void setModuleNo(Integer moduleNo) {
		this.moduleNo = moduleNo;
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
		return "Module [moduleNo=" + moduleNo + ", active=" + active + ", moduleDName=" + moduleDName + ", moduleFName="
				+ moduleFName + ", orderNo=" + orderNo + ", shortcut=" + shortcut + "]";
	}

}