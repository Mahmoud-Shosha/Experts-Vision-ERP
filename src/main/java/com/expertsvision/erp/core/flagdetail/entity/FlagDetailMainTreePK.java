package com.expertsvision.erp.core.flagdetail.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;


/**
 * The primary key class for the FlagDetailMainTree database table.
 * 
 */
@Embeddable
public class FlagDetailMainTreePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="flag_code")
	private String flagCode;

	@Column(name="flag_value")
	private Integer flagValue;
	
	@Column(name="user_id")
	private Integer userId;

	public FlagDetailMainTreePK() {
	}
	
	public FlagDetailMainTreePK(String flagCode, Integer flagValue, Integer userId) {
		super();
		this.flagCode = flagCode;
		this.flagValue = flagValue;
		this.userId = userId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(flagCode, flagValue, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlagDetailMainTreePK other = (FlagDetailMainTreePK) obj;
		return Objects.equals(flagCode, other.flagCode) && Objects.equals(flagValue, other.flagValue)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "FlagDetailMainTreePK [flagCode=" + flagCode + ", flagValue=" + flagValue + ", userId=" + userId + "]";
	}
	
}