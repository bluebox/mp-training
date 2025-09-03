package com.lms.web.exceptions;

public class ValidationException extends RuntimeException {
	public ValidationException(String message) {
		super(message);
	}
}
