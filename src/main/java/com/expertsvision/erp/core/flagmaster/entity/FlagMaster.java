package com.expertsvision.erp.core.flagmaster.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flag_master database table.
 * 
 */
@Entity
@Table(name="flag_master")
@NamedQuery(name="FlagMaster.findAll", query="SELECT f FROM FlagMaster f")
public class FlagMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="flag_code")
	private String flagCode;

	@Column(name="label_code")
	private String labelCode;

	@Column(name="lang_no")
	private Integer langNo;

	public FlagMaster() {
	}

	public String getFlagCode() {
		return this.flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public String getLabelCode() {
		return this.labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public Integer getLangNo() {
		return this.langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	@Override
	public String toString() {
		return "FlagMaster [flagCode=" + flagCode + ", labelCode=" + labelCode + ", langNo=" + langNo + "]";
	}

}