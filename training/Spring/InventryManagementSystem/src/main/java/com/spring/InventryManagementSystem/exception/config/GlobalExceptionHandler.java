package com.spring.InventryManagementSystem.exception.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Order(1)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validationException(MethodArgumentNotValidException e){
		Map<String,String> errors = new HashMap<>();
		errors.put("Status","Validation Error");
		errors.put("message", "The Validations are : "+e.getAllErrors().toString());
		return ResponseEntity.unprocessableEntity().body(errors);
	}
	
	@Order(1)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> exceptionHandler(IllegalArgumentException e){
		Map<String,String> globalException = new HashMap<>();
		globalException.put("status", "Error");
		globalException.put("message", "The Values Are Not Matching  : "+e.getMessage());
		return ResponseEntity.unprocessableEntity().body(globalException);
	}
	
	@Order(1)
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> sqlException(SQLException e){
		Map<String,String> globalException = new HashMap<>();
		globalException.put("status", "Error");
		globalException.put("message", "Error in Repository "+e.getMessage());
		return ResponseEntity.unprocessableEntity().body(globalException);
	}
	
	@Order(1)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> HttpMethodError(HttpRequestMethodNotSupportedException e){
		Map<String,String> globalException = new HashMap<>();
		globalException.put("status", "Request Method Not Supported ");
		globalException.put("message", "Check Request Method  : "+e.getMessage());
		return ResponseEntity.badRequest().body(globalException);
	}
	
	@Order(1)
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> NotSupportedMethod(HttpMediaTypeNotSupportedException e){
		Map<String,String> globalException = new HashMap<>();
		globalException.put("status", "Error");
		globalException.put("status", "UnSpported Media Type");
		globalException.put("message", "The Enum values are Not Matching  : "+e.getMessage());
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(globalException);
	}
	
	@Order(1)
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<?> paramsNotFound(MissingServletRequestParameterException e){
		Map<String,String> globalException = new HashMap<>();
		globalException.put("status", "Error");
		globalException.put("message", "Not Found All Required Params");
		globalException.put("message", "The Enum values are Not Matching  : "+e.getMessage());
		return ResponseEntity.unprocessableEntity().body(globalException);
	}
	
	@Order(2)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception e){
		Map<String,String> globalException = new HashMap<>();
		globalException.put("status", "Error");
		globalException.put("message", "The Error : "+e.getMessage());
		return ResponseEntity.internalServerError().body(globalException);
	}
	
}
