package com.expertsvision.erp.core.form.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the forms_view database view.
 * 
 */
@Entity
@Table(name="forms_view")
@NamedQuery(name="FormsView.findAll", query="SELECT f FROM FormsView f")
public class FormsView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("active")
	@Column(name="active")
	private Boolean active;

	@JsonProperty("form_d_name")
	@Column(name="form_d_name")
	private String formDName;

	@JsonProperty("form_f_name")
	@Column(name="form_f_name")
	private String formFName;

	@Id
	@JsonProperty("form_no")
	@Column(name="form_no")
	private Integer formNo;

	@JsonProperty("form_order")
	@Column(name="form_order")
	private Integer formOrder;

	@JsonProperty("main")
	@Column(name="main")
	private Boolean main;

	@JsonProperty("module_no")
	@Column(name="module_no")
	private Integer moduleNo;

	@JsonProperty("module_no_d_name")
	@Column(name="module_no_d_name")
	private String moduleNoDName;

	@JsonProperty("module_no_f_name")
	@Column(name="module_no_f_name")
	private String moduleNoFName;

	@JsonProperty("parent_form")
	@Column(name="parent_form")
	private Integer parentForm;

	@JsonProperty("parent_form_d_name")
	@Column(name="parent_form_d_name")
	private String parentFormDName;

	@JsonProperty("parent_form_f_name")
	@Column(name="parent_form_f_name")
	private String parentFormFName;

	public FormsView() {
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFormDName() {
		return this.formDName;
	}

	public void setFormDName(String formDName) {
		this.formDName = formDName;
	}

	public String getFormFName() {
		return this.formFName;
	}

	public void setFormFName(String formFName) {
		this.formFName = formFName;
	}

	public Integer getFormNo() {
		return this.formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	public Integer getFormOrder() {
		return this.formOrder;
	}

	public void setFormOrder(Integer formOrder) {
		this.formOrder = formOrder;
	}

	public Boolean getMain() {
		return this.main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public Integer getModuleNo() {
		return this.moduleNo;
	}

	public void setModuleNo(Integer moduleNo) {
		this.moduleNo = moduleNo;
	}

	public String getModuleNoDName() {
		return this.moduleNoDName;
	}

	public void setModuleNoDName(String moduleNoDName) {
		this.moduleNoDName = moduleNoDName;
	}

	public String getModuleNoFName() {
		return this.moduleNoFName;
	}

	public void setModuleNoFName(String moduleNoFName) {
		this.moduleNoFName = moduleNoFName;
	}

	public Integer getParentForm() {
		return this.parentForm;
	}

	public void setParentForm(Integer parentForm) {
		this.parentForm = parentForm;
	}

	public String getParentFormDName() {
		return this.parentFormDName;
	}

	public void setParentFormDName(String parentFormDName) {
		this.parentFormDName = parentFormDName;
	}

	public String getParentFormFName() {
		return this.parentFormFName;
	}

	public void setParentFormFName(String parentFormFName) {
		this.parentFormFName = parentFormFName;
	}

	@Override
	public String toString() {
		return "FormsView [active=" + active + ", formDName=" + formDName + ", formFName=" + formFName + ", formNo="
				+ formNo + ", formOrder=" + formOrder + ", main=" + main + ", moduleNo=" + moduleNo + ", moduleNoDName="
				+ moduleNoDName + ", moduleNoFName=" + moduleNoFName + ", parentForm=" + parentForm
				+ ", parentFormDName=" + parentFormDName + ", parentFormFName=" + parentFormFName + "]";
	}

}