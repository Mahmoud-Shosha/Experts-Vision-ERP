package com.expertsvision.erp.core.flagpriv.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the flag_priv database table.
 * 
 */
@Embeddable
public class FlagPrivPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="user_id")
	private Integer userId;

	@Column(name="flag_code")
	private String flagCode;

	@Column(name="flag_value")
	private Integer flagValue;

	public FlagPrivPK() {
	}
	
	public FlagPrivPK(Integer userId, String flagCode, Integer flagValue) {
		super();
		this.userId = userId;
		this.flagCode = flagCode;
		this.flagValue = flagValue;
	}

	public Integer getUserId() {
		return this.userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
		if (!(other instanceof FlagPrivPK)) {
			return false;
		}
		FlagPrivPK castOther = (FlagPrivPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.flagCode.equals(castOther.flagCode)
			&& this.flagValue.equals(castOther.flagValue);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.flagCode.hashCode();
		hash = hash * prime + this.flagValue.hashCode();
		
		return hash;
	}

	@Override
	public String toString() {
		return "FlagPrivPK [userId=" + userId + ", flagCode=" + flagCode + ", flagValue=" + flagValue + "]";
	}
	
}