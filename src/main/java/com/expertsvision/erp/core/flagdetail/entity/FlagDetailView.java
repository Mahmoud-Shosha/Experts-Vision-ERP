package com.expertsvision.erp.core.flagdetail.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the flag_detail_view database view.
 * 
 */
@Entity
@Table(name="flag_detail_view")
@IdClass(FlagDetailPK.class)
@NamedQuery(name="FlagDetailView.findAll", query="SELECT f FROM FlagDetailView f")
public class FlagDetailView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("active")
	@Column(name="active")
	private Boolean active;

	@Id
	@JsonProperty("flag_code")
	@Column(name="flag_code")
	private String flagCode;

	@JsonProperty("flag_priv")
	@Column(name="flag_priv")
	private Boolean flagPriv;

	@JsonProperty("flag_sr")
	@Column(name="flag_sr")
	private Integer flagSr;

	@Id
	@JsonProperty("flag_value")
	@Column(name="flag_value")
	private Integer flagValue;

	@JsonProperty("label_code")
	@Column(name="label_code")
	private String labelCode;

	public FlagDetailView() {
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFlagCode() {
		return this.flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
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

	public Integer getFlagValue() {
		return this.flagValue;
	}

	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
	}

	public String getLabelCode() {
		return this.labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	@Override
	public String toString() {
		return "FlagDetailView [active=" + active + ", flagCode=" + flagCode + ", flagPriv=" + flagPriv + ", flagSr="
				+ flagSr + ", flagValue=" + flagValue + ", labelCode=" + labelCode + "]";
	}

}