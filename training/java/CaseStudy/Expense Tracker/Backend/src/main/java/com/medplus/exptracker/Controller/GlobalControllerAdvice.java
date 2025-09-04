package com.medplus.exptracker.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.medplus.exptracker.Exceptions.MonthlyLimitException;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String,String>> HttpMessageNotReadableException(HttpMessageNotReadableException e){
		var res = new HashMap<String,String >();
		res.put("message", "The input doesn't match the requirements!");
		return ResponseEntity.ok(res);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
		var res = new HashMap<String,String >();
		List<String> errors = e.getBindingResult()
		        .getFieldErrors()
		        .stream()
		        .map(FieldError::getDefaultMessage)
		        .toList();
		
		String errorsString = String.join("\n", errors);
		
		res.put("message", errorsString);
		return new ResponseEntity<>(res,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(MonthlyLimitException.class)
	public ResponseEntity<Map<String,String>> handleMonthlyLimitException(MonthlyLimitException e){
		var res = new HashMap<String,String >();
		res.put("message", e.getMessage());
		return new ResponseEntity<>(res,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> handleGlobalException(Exception e){
		var res = new HashMap<String,String >();
		res.put("message", e.getMessage());
		return new ResponseEntity<>(res,HttpStatus.NOT_ACCEPTABLE);
	}
	

	
}
