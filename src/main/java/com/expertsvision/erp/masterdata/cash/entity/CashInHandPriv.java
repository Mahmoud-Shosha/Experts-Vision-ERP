package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cash_in_hand_priv database table.
 * 
 */
@Entity
@Table(name="cash_in_hand_priv")
@NamedQuery(name="CashInHandPriv.findAll", query="SELECT c FROM CashInHandPriv c")
public class CashInHandPriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CashInHandPrivPK id;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_priv")
	private Boolean addPriv;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="cash_no")
	private Integer cashNo;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="view_priv")
	private Boolean viewPriv;

	public CashInHandPriv() {
	}

	public CashInHandPrivPK getId() {
		return this.id;
	}

	public void setId(CashInHandPrivPK id) {
		this.id = id;
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

	public Integer getCashNo() {
		return this.cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
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

}