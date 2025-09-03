package com.rms.restaurant_management_system_backend.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rms.restaurant_management_system_backend.utilities.CustomResponse;

@ControllerAdvice
@Order(1)
public class GlobalExceptionHandler {
	@ExceptionHandler(RestaurantOperationException.class)
	public ResponseEntity<CustomResponse> handleDatabaseOperationException(RestaurantOperationException e) {
		return buildResponse(HttpStatus.CONFLICT, false, e.getMessage());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
				.collect(Collectors.toList());
		return buildResponse(HttpStatus.BAD_REQUEST, false, errors);
	}

	private ResponseEntity<CustomResponse> buildResponse(HttpStatus statusCode, Boolean status, String message) {
		return buildResponse(statusCode, status, List.of(message));
	}

	private ResponseEntity<CustomResponse> buildResponse(HttpStatus statusCode, boolean status, List<String> messages) {
		CustomResponse body = new CustomResponse(status, messages, null);
		return new ResponseEntity<>(body, statusCode);
	}
}
