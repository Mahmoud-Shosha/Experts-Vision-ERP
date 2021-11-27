package com.expertsvision.erp.core.exception;

import java.util.Map;

public class ExcelValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Map<Integer, Object> errorsMap;

	public ExcelValidationException(Map<Integer, Object> errorsMap) {
		this.errorsMap = errorsMap;
	}

	public Map<Integer, Object> getErrorsMap() {
		return errorsMap;
	}

}
