package com.expertsvision.erp.masterdata.accpara.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the acc_para database table.
 * 
 */
@Entity
@Table(name="acc_para")
@NamedQuery(name="AccPara.findAll", query="SELECT a FROM AccPara a")
public class AccPara implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="para_no")
	private Integer paraNo;

	@Column(name="cc_post")
	private String ccPost;

	@Column(name="sub_acc_lvl")
	private Integer subAccLvl;

	public AccPara() {
	}

	public Integer getParaNo() {
		return this.paraNo;
	}

	public void setParaNo(Integer paraNo) {
		this.paraNo = paraNo;
	}

	public String getCcPost() {
		return this.ccPost;
	}

	public void setCcPost(String ccPost) {
		this.ccPost = ccPost;
	}

	public Integer getSubAccLvl() {
		return this.subAccLvl;
	}

	public void setSubAccLvl(Integer subAccLvl) {
		this.subAccLvl = subAccLvl;
	}

	@Override
	public String toString() {
		return "AccPara [paraNo=" + paraNo + ", ccPost=" + ccPost + ", subAccLvl=" + subAccLvl + "]";
	}

}