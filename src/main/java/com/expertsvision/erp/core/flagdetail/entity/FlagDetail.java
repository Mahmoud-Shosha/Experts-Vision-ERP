package com.expertsvision.erp.core.flagdetail.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the flag_detail database table.
 * 
 */
@Entity
@Table(name="flag_detail")
@IdClass(FlagDetailPK.class)
@NamedQuery(name="FlagDetail.findAll", query="SELECT f FROM FlagDetail f")
public class FlagDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="flag_code")
	private String flagCode;

	@Id
	@Column(name="flag_value")
	private Integer flagValue;

	@Column(name="active")
	private Boolean active;

	@Column(name="flag_priv")
	private Boolean flagPriv;

	@Column(name="flag_sr")
	private Integer flagSr;

	@Column(name="label_code")
	private String labelCode;

	@Column(name="lang_no")
	private Integer langNo;

	public FlagDetail() {
	}

	public String getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getFlagPriv() {
		return this.flagPriv;
	}

	public void setFlagPriv(Boolean flagPriv) {
		this.flagPriv = flagPriv;
	}

	public Integer getFlagSr() {
		return this.flagSr;
	}

	public void setFlagSr(Integer flagSr) {
		this.flagSr = flagSr;
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
		return "FlagDetail [flagCode=" + flagCode + ", flagValue=" + flagValue + ", active=" + active + ", flagPriv="
				+ flagPriv + ", flagSr=" + flagSr + ", labelCode=" + labelCode + ", langNo=" + langNo + "]";
	}

}