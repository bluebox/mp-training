package com.example.Backend.exceptions;

public class InvalidDetailsException extends RuntimeException {

	public InvalidDetailsException(String msg) {
		super(msg);
	}
}
