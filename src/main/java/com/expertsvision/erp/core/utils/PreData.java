package com.expertsvision.erp.core.utils;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PreData {

	@JsonProperty("read_only")
	private Set<String> readOnly;
	
	@JsonProperty("default_values")
	private Map<String, Object> defaultValues;
	
	@JsonProperty("info")
	private Map<String, Object> info;

	
	public PreData() {
		super();
	}

	public PreData(Set<String> readOnly, Map<String, Object> defaultValues, Map<String, Object> info) {
		super();
		this.readOnly = readOnly;
		this.defaultValues = defaultValues;
		this.info = info;
	}

	public Set<String> getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Set<String> readOnly) {
		this.readOnly = readOnly;
	}

	public Map<String, Object> getDefaultValues() {
		return defaultValues;
	}

	public void setDefaultValues(Map<String, Object> defaultValues) {
		this.defaultValues = defaultValues;
	}

	public Map<String, Object> getInfo() {
		return info;
	}

	public void setInfo(Map<String, Object> info) {
		this.info = info;
	}
	
}
