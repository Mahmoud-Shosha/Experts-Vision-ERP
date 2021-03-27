package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class UnauthorizedExceptionAdvice {
	
	@Autowired
	private Response response;
	

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> GeneralHandler(UnauthorizedException e) {
		return response.response("donot_have_privileges", e.getLabelCode(), HttpStatus.UNAUTHORIZED);
	}

}
