package com.expertsvision.erp.core.exception;

public class UnauthorizedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String labelCode;
	
	public UnauthorizedException(String labelCode) {
		this.labelCode = labelCode;
	}


	public String getLabelCode() {
		return labelCode;
	}

}
