package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.ConfirmException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class ConfirmExceptionAdvice {
	
	@Autowired
	private Response response;
	

	@ExceptionHandler(ConfirmException.class)
	public ResponseEntity<Object> GeneralHandler(ConfirmException e) {
		if (e.getLabelCode() != null) {
			return response.response(e.getMessageCode(), e.getLabelCode(), HttpStatus.CONFLICT);
		} else {
			return response.response(e.getMessageCode(), HttpStatus.CONFLICT);
		}
	}

}
