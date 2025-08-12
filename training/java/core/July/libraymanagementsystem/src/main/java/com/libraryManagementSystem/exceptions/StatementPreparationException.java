package com.libraryManagementSystem.exceptions;

public class StatementPreparationException extends Exception {
	private static final long serialVersionUID = 1L;

	public StatementPreparationException(String message) {
		super(message);
	}

	public StatementPreparationException(String message, Throwable cause) {
		super(message, cause);
	}
}
