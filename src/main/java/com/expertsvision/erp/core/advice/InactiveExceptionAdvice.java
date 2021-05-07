package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.InactiveException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class InactiveExceptionAdvice {
	
	@Autowired
	private Response response;
	

	@ExceptionHandler(InactiveException.class)
	public ResponseEntity<Object> GeneralHandler(InactiveException e) {
		return response.response("is_inactive", e.getLabelCode(), HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
	}

}
