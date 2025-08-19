package com.library.library_management_system.exception;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException {

	public MemberNotFoundException(String message) {
		super(message);
	}

}
