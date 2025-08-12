package com.libraryManagementSystem.exceptions;

public class InvalidBookDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidBookDataException(String message) {
		super(message);
	}

	public InvalidBookDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
