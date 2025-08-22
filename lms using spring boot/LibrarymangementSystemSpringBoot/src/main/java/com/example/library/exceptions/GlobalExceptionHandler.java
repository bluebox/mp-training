package com.example.library.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", "error");
		body.put("message", ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(InvalidDetailsException.class)
	public ResponseEntity<Map<String, Object>> handleInvalid(InvalidDetailsException ex) {
		return ResponseEntity.badRequest().body(Map.of("status", "error", "message", ex.getMessage()));
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<Map<String, Object>> handleDb(DatabaseException ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("status", "error", "message", ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleOther(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(Map.of("status", "error", "message", "Unexpected error"));
	}
}
