package com.expertsvision.erp.core.label.entity;

import java.io.Serializable;

/**
 * The primary key class for the labels_view database view.
 * 
 */
public class LabelsViewPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer langNo;

	private String labelCode;
	

	public LabelsViewPK() {
	}
	
	
	public LabelsViewPK(Integer langNo, String labelCode) {
		super();
		this.langNo = langNo;
		this.labelCode = labelCode;
	}


	public Integer getLangNo() {
		return this.langNo;
	}
	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}
	public String getLabelCode() {
		return this.labelCode;
	}
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	@Override
	public String toString() {
		return "LabelsViewPK [langNo=" + langNo + ", labelCode=" + labelCode + "]";
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LabelsViewPK)) {
			return false;
		}
		LabelsViewPK castOther = (LabelsViewPK)other;
		return 
			this.langNo.equals(castOther.langNo)
			&& this.labelCode.equals(castOther.labelCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.langNo.hashCode();
		hash = hash * prime + this.labelCode.hashCode();
		
		return hash;
	}
}