package com.expertsvision.erp.masterdata.zone.dto;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ZoneViewFilter {
	
	@JsonProperty("zone_no")
	private Integer zoneNo;

	@JsonProperty("city_no")
	private Integer cityNo;

	@JsonProperty("zone_d_name")
	private String zoneDName;

	@JsonProperty("zone_f_name")
	private String zoneFName;

	public Integer getZoneNo() {
		return zoneNo;
	}

	public void setZoneNo(Integer zoneNo) {
		this.zoneNo = zoneNo;
	}

	public Integer getCityNo() {
		return cityNo;
	}

	public void setCityNo(Integer cityNo) {
		this.cityNo = cityNo;
	}

	public String getZoneDName() {
		return zoneDName;
	}

	public void setZoneDName(String zoneDName) {
		this.zoneDName = zoneDName;
	}

	public String getZoneFName() {
		return zoneFName;
	}

	public void setZoneFName(String zoneFName) {
		this.zoneFName = zoneFName;
	}

	@Override
	public String toString() {
		return "ZoneViewFilter [zoneNo=" + zoneNo + ", cityNo=" + cityNo + ", zoneDName=" + zoneDName + ", zoneFName="
				+ zoneFName + "]";
	}
	
}
