package com.expertsvision.erp.core.flagdetail.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the flag_detail database table.
 * 
 */
@Embeddable
public class FlagDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="flag_code")
	private String flagCode;

	@Column(name="flag_value")
	private Integer flagValue;

	public FlagDetailPK() {
	}
	
	public FlagDetailPK(String flagCode, Integer flagValue) {
		super();
		this.flagCode = flagCode;
		this.flagValue = flagValue;
	}

	public String getFlagCode() {
		return this.flagCode;
	}
	
	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}
	
	public Integer getFlagValue() {
		return this.flagValue;
	}
	
	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FlagDetailPK)) {
			return false;
		}
		FlagDetailPK castOther = (FlagDetailPK)other;
		return 
			this.flagCode.equals(castOther.flagCode)
			&& this.flagValue.equals(castOther.flagValue);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.flagCode.hashCode();
		hash = hash * prime + this.flagValue.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "FlagDetailPK [flagCode=" + flagCode + ", flagValue=" + flagValue + "]";
	}
	
}