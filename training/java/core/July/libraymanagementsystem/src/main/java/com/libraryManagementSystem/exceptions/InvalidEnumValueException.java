package com.libraryManagementSystem.exceptions;

public class InvalidEnumValueException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidEnumValueException(String enumType) {
		super("Please select a" + enumType);
	}
}
