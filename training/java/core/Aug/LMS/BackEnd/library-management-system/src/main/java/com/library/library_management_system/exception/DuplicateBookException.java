package com.library.library_management_system.exception;

public class DuplicateBookException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateBookException(String message) {
		super(message);
	}

	public DuplicateBookException(String message, Throwable cause) {
		super(message, cause);
	}
}
