package com.kbc.KbcApp.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	private static final String FAILURE = "failure";
	
	@ExceptionHandler(Exception.class)
	private ResponseEntity<Map<String,Object>> createErrorResponse(Exception e) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", FAILURE);
		response.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(KbcException.class)
	private ResponseEntity<Map<String,Object>> handleKbcException(Exception e) {
		Map<String, Object> response = new HashMap<>();
		response.put("status", FAILURE);
		response.put("message", e.getMessage());
		log.error("Error : {}", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
