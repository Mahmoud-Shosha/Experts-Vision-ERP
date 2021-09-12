package com.expertsvision.erp.masterdata.branches.entity;

import java.io.Serializable;

/**
 * The primary key class for the branches_priv database table.
 * 
 */
public class BranchesPrivPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private Integer branchNo;

	public BranchesPrivPK() {
	}
	
	public BranchesPrivPK(Integer userId, Integer branchNo) {
		this.userId = userId;
		this.branchNo = branchNo;
	}

	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBranchNo() {
		return this.branchNo;
	}
	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BranchesPrivPK)) {
			return false;
		}
		BranchesPrivPK castOther = (BranchesPrivPK)other;
		return 
			this.userId.equals(castOther.userId)
			&& this.branchNo.equals(castOther.branchNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userId.hashCode();
		hash = hash * prime + this.branchNo.hashCode();
		
		return hash;
	}
}