package com.expertsvision.erp.core.exception;

public class InactiveException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final String labelCode;
	
	public InactiveException(String labelCode) {
		this.labelCode = labelCode;
	}

	public String getLabelCode() {
		System.out.println(labelCode);
		return labelCode;
	}

}
