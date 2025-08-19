package dev.tulasidhar.lms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.tulasidhar.lms.Exceptions.DBConstrainsException;
import dev.tulasidhar.lms.Exceptions.IdNotExistException;
import dev.tulasidhar.lms.Exceptions.NoRowsEffectedException;
import dev.tulasidhar.lms.Exceptions.ValidationException;

@ControllerAdvice
public class AllControllerAdvice {
	
	@ExceptionHandler(DBConstrainsException.class)
	public ResponseEntity<Object> handleException(DBConstrainsException e) {
		Map<String,String> response = new HashMap<String,String>();
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Object> handleException(ValidationException e) {
		Map<String,String> response = new HashMap<String,String>();
		System.out.println("Came here");
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<Object> handleException(DataAccessException e) {
		Map<String,String> response = new HashMap<String,String>();
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(NoRowsEffectedException.class)
	public ResponseEntity<Object> handleException(NoRowsEffectedException e) {
		Map<String,String> response = new HashMap<String,String>();
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(IdNotExistException.class)
	public ResponseEntity<Object> handleException(IdNotExistException e) {
		Map<String,String> response = new HashMap<String,String>();
		response.put("message", e.getMessage());
		return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
	}
}
