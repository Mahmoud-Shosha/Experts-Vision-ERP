package com.expertsvision.erp.masterdata.employees.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmpInfoViewFilter {
	
	@JsonProperty("emp_no")
	private Integer empNo;

	@JsonProperty("emp_d_name")
	private String empDName;

	@JsonProperty("emp_f_name")
	private String empFName;

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getEmpDName() {
		return empDName;
	}

	public void setEmpDName(String empDName) {
		this.empDName = empDName;
	}

	public String getEmpFName() {
		return empFName;
	}

	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}

	@Override
	public String toString() {
		return "EmpInfoViewFilter [empNo=" + empNo + ", empDName=" + empDName + ", empFName=" + empFName + "]";
	}

}
