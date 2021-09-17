package com.expertsvision.erp.masterdata.currency.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the currency_values database table.
 * 
 */
@Entity
@Table(name="currency_values")
@IdClass(CurrencyValuePK.class)
@NamedQuery(name="CurrencyValue.findAll", query="SELECT c FROM CurrencyValue c")
public class CurrencyValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="currency_code")
	private String currencyCode;

	@Id
	@Column(name="value")
	private Integer value;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	public CurrencyValue() {
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
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

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CurrencyValue [currencyCode=" + currencyCode + ", value=" + value + ", addDate=" + addDate
				+ ", addUser=" + addUser + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + "]";
	}

}