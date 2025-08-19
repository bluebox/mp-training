package com.example.web.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String, String>> handilingValidationException(ValidationException e) {
		Map<String, String> response = new HashMap<>();
        response.put("error", "Validation Failed");
        response.put("message", e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
