package com.expertsvision.erp.masterdata.branches.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the branches_priv database table.
 * 
 */
@Entity
@Table(name="branches_priv")
@IdClass(BranchesPrivPK.class)
@NamedQuery(name="BranchesPriv.findAll", query="SELECT b FROM BranchesPriv b")
public class BranchesPriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private Integer userId;

	@Id
	@Column(name="branch_no")
	private Integer branchNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_priv")
	private Boolean addPriv;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="view_priv")
	private Boolean viewPriv;

	public BranchesPriv() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Boolean getAddPriv() {
		return this.addPriv;
	}

	public void setAddPriv(Boolean addPriv) {
		this.addPriv = addPriv;
	}

	public Integer getAddUser() {
		return this.addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

	
	@Override
	public String toString() {
		return "BranchesPriv [userId=" + userId + ", branchNo=" + branchNo + ", addDate=" + addDate + ", addPriv="
				+ addPriv + ", addUser=" + addUser + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", viewPriv=" + viewPriv + "]";
	}

}