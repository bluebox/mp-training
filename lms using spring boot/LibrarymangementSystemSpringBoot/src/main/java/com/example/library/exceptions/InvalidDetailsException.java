package com.example.library.exceptions;

public class InvalidDetailsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidDetailsException(String msg) {
		super(msg);
	}
}
