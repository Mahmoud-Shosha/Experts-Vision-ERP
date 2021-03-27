package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class ValidationExceptionAdvice {
	
	@Autowired
	private Response response;
	

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> GeneralHandler(ValidationException e) {
		if ((e.getLabelCode() != null) && (e.getValueCode() != null)) {
			return response.response(e.getMessageCode(), e.getLabelCode(), e.getValueCode(), HttpStatus.BAD_REQUEST);
		} if ((e.getLabelCode() != null) && (e.getValue() != null)) {
			return response.response(e.getMessageCode(), e.getLabelCode(), e.getValue(), HttpStatus.BAD_REQUEST);
		} else if (e.getLabelCode() != null) {
			return response.response(e.getMessageCode(), e.getLabelCode(), HttpStatus.BAD_REQUEST);
		} else {
			return response.response(e.getMessageCode(), HttpStatus.BAD_REQUEST);
		}
	}

}
