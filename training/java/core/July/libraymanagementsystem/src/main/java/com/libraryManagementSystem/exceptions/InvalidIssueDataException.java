package com.libraryManagementSystem.exceptions;

public class InvalidIssueDataException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidIssueDataException(String message) {
		super(message);
	}

	public InvalidIssueDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
