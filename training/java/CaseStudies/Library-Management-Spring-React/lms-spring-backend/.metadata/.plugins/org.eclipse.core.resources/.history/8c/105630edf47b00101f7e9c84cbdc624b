package dev.tulasidhar.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.tulasidhar.lms.Exceptions.DBConstrainsException;

@ControllerAdvice
public class AllControllerAdvice {
	
	@ExceptionHandler(DBConstrainsException.class)
	
	
	public ResponseEntity<String> handleException(DBConstrainsException e) {
		return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
}
