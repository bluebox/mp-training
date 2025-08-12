package com.libraryManagementSystem.exceptions;

public class InvalidMemberDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidMemberDataException(String message) {
		super(message);
	}

	public InvalidMemberDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
