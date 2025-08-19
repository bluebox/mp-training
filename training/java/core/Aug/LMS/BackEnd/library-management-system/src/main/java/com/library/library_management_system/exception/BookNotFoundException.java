package com.library.library_management_system.exception;

@SuppressWarnings("serial")
public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(String message) {
		super(message);
	}

}
