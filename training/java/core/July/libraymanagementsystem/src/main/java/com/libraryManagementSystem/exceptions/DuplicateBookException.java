package com.libraryManagementSystem.exceptions;

public class DuplicateBookException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateBookException(String message) {
		super(message);
	}

	public DuplicateBookException(String message, Throwable cause) {
		super(message, cause);
	}
}
