package com.libraryManagementSystem.exceptions;

@SuppressWarnings("serial")
public class BookNotFoundException extends Exception {

	public BookNotFoundException(String message) {
		super(message);
	}

	public BookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
