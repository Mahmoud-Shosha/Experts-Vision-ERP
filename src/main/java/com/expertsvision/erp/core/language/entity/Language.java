package com.expertsvision.erp.core.language.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the language database table.
 * 
 */
@Entity
@Table(name="language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lang_no")
	private Integer langNo;

	@Column(name="active")
	private Boolean active;

	@Column(name="lang_dfl")
	private Boolean langDfl;

	@Column(name="lang_dir")
	private Integer langDir;

	@Column(name="lang_ext")
	private String langExt;

	@Column(name="lang_name")
	private String langName;

	@Column(name="report_ext")
	private String reportExt;

	public Language() {
	}

	public Integer getLangNo() {
		return this.langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
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

	public String getReportExt() {
		return this.reportExt;
	}

	public void setReportExt(String reportExt) {
		this.reportExt = reportExt;
	}

	@Override
	public String toString() {
		return "Language [langNo=" + langNo + ", active=" + active + ", langDfl=" + langDfl + ", langDir=" + langDir
				+ ", langExt=" + langExt + ", langName=" + langName + ", reportExt=" + reportExt + "]";
	}

}