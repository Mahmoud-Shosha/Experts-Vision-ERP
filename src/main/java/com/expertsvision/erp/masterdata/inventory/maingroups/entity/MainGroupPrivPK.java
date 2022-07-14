package com.expertsvision.erp.masterdata.inventory.maingroups.entity;

import java.io.Serializable;

/**
 * The primary key class for the main_group_priv database table.
 * 
 */
public class MainGroupPrivPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String groupCode;

	public MainGroupPrivPK() {
	}

	public MainGroupPrivPK(Integer userId, String groupCode) {
		super();
		this.userId = userId;
		this.groupCode = groupCode;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MainGroupPrivPK)) {
			return false;
		}
		MainGroupPrivPK castOther = (MainGroupPrivPK) other;
		return this.userId.equals(castOther.userId) && this.groupCode.equals(castOther.groupCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.groupCode.hashCode();

		return hash;
	}
}