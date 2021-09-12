package com.expertsvision.erp.core.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expertsvision.erp.core.exception.DetailValidationException;
import com.expertsvision.erp.core.response.Response;

@RestControllerAdvice
public class DetailValidationExceptionAdvice {

	@Autowired
	private Response response;

	@ExceptionHandler(DetailValidationException.class)
	public ResponseEntity<Object> GeneralHandler(DetailValidationException e) {
		return response.response(e.getMessageCode(), e.getLabelCodeFirst(), e.getValueFirst(), e.getLabelCodeSecond(),
				e.getValueSecond(), HttpStatus.BAD_REQUEST);
	}

}
