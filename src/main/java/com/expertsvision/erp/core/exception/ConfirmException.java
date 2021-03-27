package com.expertsvision.erp.core.exception;

public class ConfirmException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String messageCode;
	private final String labelCode;
	
	
	public ConfirmException(String messageCode, String labelCode) {
		this.messageCode = messageCode;
		this.labelCode = labelCode;
	}
	
	public ConfirmException(String messageCode) {
		this(messageCode, null);
	}
	

	public String getMessageCode() {
		return messageCode;
	}

	public String getLabelCode() {
		return labelCode;
	}

	

}
