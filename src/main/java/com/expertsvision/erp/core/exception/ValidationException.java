package com.expertsvision.erp.core.exception;

public class ValidationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private final String messageCode;
	private final String labelCode;
	private final String valueCode;
	private final Object value;
	
	public ValidationException(String messageCode, String labelCode, Object value) {
		this.messageCode = messageCode;
		this.labelCode = labelCode;
		this.valueCode = null;
		this.value = value;
	}
	
	public ValidationException(String messageCode, String labelCode, String valueCode) {
		this.messageCode = messageCode;
		this.labelCode = labelCode;
		this.valueCode = valueCode;
		this.value = null;
	}
	
	public ValidationException(String messageCode, String labelCode) {
		this(messageCode, labelCode, null);
	}
	
	public ValidationException(String messageCode) {
		this(messageCode, null);
	}

	public String getMessageCode() {
		return messageCode;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public Object getValue() {
		return value;
	}

	public String getValueCode() {
		return valueCode;
	}

}
