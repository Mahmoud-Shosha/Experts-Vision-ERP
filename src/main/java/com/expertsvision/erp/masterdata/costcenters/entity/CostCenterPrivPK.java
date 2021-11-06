package com.expertsvision.erp.masterdata.costcenters.entity;

import java.io.Serializable;

/**
 * The primary key class for the cost_center_priv database table.
 * 
 */
public class CostCenterPrivPK implements Serializable {

	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer costCenter;

	public CostCenterPrivPK() {
	}

	public CostCenterPrivPK(Integer userId, Integer costCenter) {
		super();
		this.userId = userId;
		this.costCenter = costCenter;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCostCenter() {
		return this.costCenter;
	}

	public void setCostCenter(Integer costCenter) {
		this.costCenter = costCenter;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CostCenterPrivPK)) {
			return false;
		}
		CostCenterPrivPK castOther = (CostCenterPrivPK) other;
		return this.userId.equals(castOther.userId) && this.costCenter.equals(castOther.costCenter);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.costCenter.hashCode();

		return hash;
	}
}