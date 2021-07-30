package com.expertsvision.erp.masterdata.province.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ProvinceViewFilter {
	
	@JsonProperty("province_no")
	private Integer provinceNo;

	@JsonProperty("country_no")
	private Integer countryNo;

	@JsonProperty("province_d_name")
	private String provinceDName;

	@JsonProperty("province_f_name")
	private String provinceFName;

	@JsonProperty("shortcut")
	private String shortcut;

	public Integer getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	public Integer getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	public String getProvinceDName() {
		return provinceDName;
	}

	public void setProvinceDName(String provinceDName) {
		this.provinceDName = provinceDName;
	}

	public String getProvinceFName() {
		return provinceFName;
	}

	public void setProvinceFName(String provinceFName) {
		this.provinceFName = provinceFName;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "ProvinceViewFilter [provinceNo=" + provinceNo + ", countryNo=" + countryNo + ", provinceDName="
				+ provinceDName + ", provinceFName=" + provinceFName + ", shortcut=" + shortcut + "]";
	}
	
}
