package com.expertsvision.erp.masterdata.region.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

public class RegionViewFilter {
	
	@JsonProperty("region_no")
	private Integer regionNo;

	@JsonProperty("region_d_name")
	private String regionDName;

	@JsonProperty("region_f_name")
	private String regionFName;

	@JsonProperty("shortcut")
	private String shortcut;
	
	public Integer getRegionNo() {
		return regionNo;
	}

	public void setRegionNo(Integer regionNo) {
		this.regionNo = regionNo;
	}

	public String getRegionDName() {
		return regionDName;
	}

	public void setRegionDName(String regionDName) {
		this.regionDName = regionDName;
	}

	public String getRegionFName() {
		return regionFName;
	}

	public void setRegionFName(String regionFName) {
		this.regionFName = regionFName;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}

	@Override
	public String toString() {
		return "RegionViewFilter [regionNo=" + regionNo + ", regionDName=" + regionDName + ", regionFName="
				+ regionFName + ", shortcut=" + shortcut + "]";
	}
	
}
