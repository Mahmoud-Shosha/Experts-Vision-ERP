package com.expertsvision.erp.masterdata.country.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryViewFilter {
	
	@JsonProperty("country_no")
	private Integer countryNo;

	@JsonProperty("country_d_name")
	private String countryDName;

	@JsonProperty("country_f_name")
	private String countryFName;

	@JsonProperty("region_no")
	private Integer regionNo;

	@JsonProperty("shortcut")
	private String shortcut;

	public Integer getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(Integer countryNo) {
		this.countryNo = countryNo;
	}

	public String getCountryDName() {
		return countryDName;
	}

	public void setCountryDName(String countryDName) {
		this.countryDName = countryDName;
	}

	public String getCountryFName() {
		return countryFName;
	}

	public void setCountryFName(String countryFName) {
		this.countryFName = countryFName;
	}

	public Integer getRegionNo() {
		return regionNo;
	}

	public void setRegionNo(Integer regionNo) {
		this.regionNo = regionNo;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "CountryViewFilter [countryNo=" + countryNo + ", countryDName=" + countryDName + ", countryFName="
				+ countryFName + ", regionNo=" + regionNo + ", shortcut=" + shortcut + "]";
	}
	
}
