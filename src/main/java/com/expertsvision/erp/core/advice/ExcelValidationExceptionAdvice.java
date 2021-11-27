package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.ExcelValidationException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class ExcelValidationExceptionAdvice {

	@Autowired
	private Response response;

	@ExceptionHandler(ExcelValidationException.class)
	public ResponseEntity<Object> GeneralHandler(ExcelValidationException e) {
		if (e.getErrorsMap().isEmpty()) {
			return response.response(e.getErrorsMap(), HttpStatus.OK);
		} else {
			return response.response(e.getErrorsMap(), HttpStatus.BAD_REQUEST);
		}
	}

}
