package com.tivanov.offermanager.domain.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class ExceptionTranslator {
	
	private static final String DATA_NOT_FOUND = "THE DATA REQUESTED HAS NOT BEEN FOUND";
	
	@ExceptionHandler({PreconditionFailedException.class, 
		MissingServletRequestParameterException.class, 
		MethodArgumentTypeMismatchException.class,
		JsonMappingException.class,
		JsonProcessingException.class
		})
	public ResponseEntity<Object> exceptionHandler(final Exception e) {
		return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler({IllegalArgumentException.class,
		CustomerNotFoundException.class,
		OfferNotFoundException.class})
	public ResponseEntity<Object> customerNotFoundExceptionHandler(final Exception e) {
		return new ResponseEntity<>(DATA_NOT_FOUND, HttpStatus.NOT_FOUND);
	}
	
	
	
}
