package com.library.library_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.library.library_management_system.response.CustomResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomResponse<?>> handleException(Exception ex) {

		CustomResponse<String> response = new CustomResponse<>(false, "Server Not Responding", null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DatabaseOperationException.class)
	public ResponseEntity<CustomResponse<?>> handleDatabaseOperationException(DatabaseOperationException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<CustomResponse<?>> handleMissingServletRequestParameterExceptionn(
			MissingServletRequestParameterException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CustomResponse<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, "Invalid Data Format allowed only JSON", null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<CustomResponse<?>> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, "Page Not Found ", null);

		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<CustomResponse<?>> handleNoResourceFoundException(NoResourceFoundException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, "Page Not Found", null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getFieldError().getDefaultMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<CustomResponse<?>> handleBookNotFoundException(BookNotFoundException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<CustomResponse<?>> handleInvalidDataFoundException(InvalidDataException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateBookException.class)
	public ResponseEntity<CustomResponse<?>> handleDuplicateBookException(DuplicateBookException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, "Book Already Exists", null);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MemberNotFoundException.class)
	public ResponseEntity<CustomResponse<?>> handleMemberNotFoundException(MemberNotFoundException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateMemberException.class)
	public ResponseEntity<CustomResponse<?>> handleDuplicateMemberException(DuplicateMemberException ex) {

		CustomResponse<String> response = new CustomResponse<>(false, ex.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
}
