package com.expertsvision.erp.masterdata.city.dto;


import com.fasterxml.jackson.annotation.JsonProperty;


public class CityViewFilter {
	
	@JsonProperty("city_no")
	private Integer cityNo;

	@JsonProperty("city_d_name")
	private String cityDName;

	@JsonProperty("city_f_name")
	private String cityFName;

	@JsonProperty("province_no")
	private Integer provinceNo;

	@JsonProperty("shortcut")
	private String shortcut;
	

	public Integer getCityNo() {
		return cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityDName() {
		return cityDName;
	}

	public void setCityDName(String cityDName) {
		this.cityDName = cityDName;
	}

	public String getCityFName() {
		return cityFName;
	}

	public void setCityFName(String cityFName) {
		this.cityFName = cityFName;
	}

	public Integer getProvinceNo() {
		return provinceNo;
	}

	public void setProvinceNo(Integer provinceNo) {
		this.provinceNo = provinceNo;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "CityViewFilter [cityNo=" + cityNo + ", cityDName=" + cityDName + ", cityFName=" + cityFName
				+ ", provinceNo=" + provinceNo + ", shortcut=" + shortcut + "]";
	}
	
}
