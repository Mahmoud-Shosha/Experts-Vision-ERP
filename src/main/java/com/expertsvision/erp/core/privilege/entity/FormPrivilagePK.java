package com.expertsvision.erp.core.privilege.entity;

import java.io.Serializable;

/**
 * The primary key class for the form_privilage database table.
 * 
 */
public class FormPrivilagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer formNo;

	public FormPrivilagePK() {
	}
	
	public FormPrivilagePK(Integer userId, Integer formNo) {
		super();
		this.userId = userId;
		this.formNo = formNo;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFormNo() {
		return this.formNo;
	}
	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
	}

	@Override
	public String toString() {
		return "FormPrivilagePK [userId=" + userId + ", formNo=" + formNo + "]";
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FormPrivilagePK)) {
			return false;
		}
		FormPrivilagePK castOther = (FormPrivilagePK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.formNo.equals(castOther.formNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.formNo.hashCode();
		
		return hash;
	}
}