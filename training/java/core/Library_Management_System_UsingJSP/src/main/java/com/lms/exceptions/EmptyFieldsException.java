package com.lms.exceptions;

public class EmptyFieldsException extends Exception{
	private static final long serialVersionUID = 1L;

	public EmptyFieldsException(String message) {
		super(message);
	}
}
