package com.lms.exceptions;

public class IdNotExistException extends Exception{
	private static final long serialVersionUID = 1L;

	public IdNotExistException(String message) {
		super(message);
	}
}
