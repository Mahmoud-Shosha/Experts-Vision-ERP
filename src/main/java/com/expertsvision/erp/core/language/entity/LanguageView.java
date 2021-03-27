package com.expertsvision.erp.core.language.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * The persistent class for the language_view database view.
 * 
 */
@Entity
@Table(name="language_view")
@NamedQuery(name="LanguageView.findAll", query="SELECT l FROM LanguageView l")
public class LanguageView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("active")
	@Column(name="active")
	private Boolean active;

	@JsonProperty("lang_dfl")
	@Column(name="lang_dfl")
	private Boolean langDfl;

	@JsonProperty("lang_dir")
	@Column(name="lang_dir")
	private Integer langDir;

	@JsonProperty("lang_ext")
	@Column(name="lang_ext")
	private String langExt;

	@JsonProperty("lang_name")
	@Column(name="lang_name")
	private String langName;

	@Id
	@JsonProperty("lang_no")
	@Column(name="lang_no")
	private Integer langNo;

	@JsonProperty("report_ext")
	@Column(name="report_ext")
	private String reportExt;
	

	public LanguageView() {
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getLangDfl() {
		return this.langDfl;
	}

	public void setLangDfl(Boolean langDfl) {
		this.langDfl = langDfl;
	}

	public Integer getLangDir() {
		return this.langDir;
	}

	public void setLangDir(Integer langDir) {
		this.langDir = langDir;
	}

	public String getLangExt() {
		return this.langExt;
	}

	public void setLangExt(String langExt) {
		this.langExt = langExt;
	}

	public String getLangName() {
		return this.langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public Integer getLangNo() {
		return this.langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	public String getReportExt() {
		return this.reportExt;
	}

	public void setReportExt(String reportExt) {
		this.reportExt = reportExt;
	}

	
	
	@Override
	public String toString() {
		return "LanguageView [active=" + active + ", langDfl=" + langDfl + ", langDir=" + langDir + ", langExt="
				+ langExt + ", langName=" + langName + ", langNo=" + langNo + ", reportExt=" + reportExt + "]";
	}

}