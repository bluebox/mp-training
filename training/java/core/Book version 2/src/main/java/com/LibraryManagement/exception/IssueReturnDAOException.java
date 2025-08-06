package com.LibraryManagement.exception;

public class IssueReturnDAOException extends RuntimeException {
	public IssueReturnDAOException(String message) {
		super(message);
	}

	public IssueReturnDAOException(String message, Throwable cause) {
		super(message, cause);
	}
}
