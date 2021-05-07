package com.expertsvision.erp.core.form.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the forms database table.
 * 
 */
@Entity
@Table(name="forms")
@NamedQuery(name="Form.findAll", query="SELECT f FROM Form f")
public class Form implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="form_no")
	private Integer formNo;
	
	@Column(name="flag_code")
	private String flagCode;

	@Column(name="active")
	private Boolean active;

	@Column(name="form_d_name")
	private String formDName;

	@Column(name="form_f_name")
	private String formFName;

	@Column(name="form_order")
	private Integer formOrder;

	@Column(name="main")
	private Boolean main;

	@Column(name="module_no")
	private Integer moduleNo;

	@Column(name="parent_form")
	private Integer parentForm;

	public Form() {
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public String getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

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

	public Integer getFormOrder() {
		return formOrder;
	}

	public void setFormOrder(Integer formOrder) {
		this.formOrder = formOrder;
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

	public Integer getParentForm() {
		return parentForm;
	}

	public void setParentForm(Integer parentForm) {
		this.parentForm = parentForm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Form [formNo=" + formNo + ", flagCode=" + flagCode + ", active=" + active + ", formDName=" + formDName
				+ ", formFName=" + formFName + ", formOrder=" + formOrder + ", main=" + main + ", moduleNo=" + moduleNo
				+ ", parentForm=" + parentForm + "]";
	}

}