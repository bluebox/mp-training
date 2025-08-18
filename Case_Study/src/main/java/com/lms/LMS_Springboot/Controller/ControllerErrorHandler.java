package com.lms.LMS_Springboot.Controller;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.Data;

 

//ControllerErrorHandler.java (example @ControllerAdvice)
@ControllerAdvice
public class ControllerErrorHandler {
	
	 @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<String> handleValidateExceptions(MethodArgumentNotValidException ex) {
	        
	        return  ResponseEntity.badRequest().body("validation error occured please enter correct details");
	    }
	 @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	    public ResponseEntity<String> handleValidateExceptions(SQLIntegrityConstraintViolationException ex) {
	        
	        return  ResponseEntity.badRequest().body("duplicate entry please enter another values");
	    }
	 
	 

 @ExceptionHandler(Exception.class)
 public ResponseEntity<String> handleExceptions(Exception ex) {
	 		
		return ResponseEntity.badRequest().body("error occured"+ex.getMessage());
 }
}

