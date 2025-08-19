package com.library.library_management_system.exception;

public class DuplicateMemberException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DuplicateMemberException(String message) {
		super(message);
	}

	public DuplicateMemberException(String message, Throwable cause) {
		super(message, cause);
	}
}
