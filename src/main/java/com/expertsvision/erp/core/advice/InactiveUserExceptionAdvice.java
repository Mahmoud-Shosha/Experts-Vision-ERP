package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.InactiveUserException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class InactiveUserExceptionAdvice {
	
	@Autowired
	private Response response;
	

	@ExceptionHandler(InactiveUserException.class)
	public ResponseEntity<Object> GeneralHandler(InactiveUserException e) {
		return response.response("is_inactive", "user", HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
	}

}
