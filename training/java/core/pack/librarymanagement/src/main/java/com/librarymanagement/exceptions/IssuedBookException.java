package com.librarymanagement.exceptions;

public class IssuedBookException extends RuntimeException {
	public IssuedBookException(String msg){
		super(msg);
	}

}
