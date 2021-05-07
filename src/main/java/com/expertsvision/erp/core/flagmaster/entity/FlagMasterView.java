package com.expertsvision.erp.core.flagmaster.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the flag_master_view database view.
 * 
 */
@Entity
@Table(name="flag_master_view")
@NamedQuery(name="FlagMasterView.findAll", query="SELECT f FROM FlagMasterView f")
public class FlagMasterView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("flag_code")
	@Column(name="flag_code")
	private String flagCode;

	@JsonProperty("label_code")
	@Column(name="label_code")
	private String labelCode;

	public FlagMasterView() {
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

	@Override
	public String toString() {
		return "FlagMasterView [flagCode=" + flagCode + ", labelCode=" + labelCode + "]";
	}

}