package com.library.library_management_system.exception;

public class DatabaseOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseOperationException(String message) {
		super(message);
	}

}
