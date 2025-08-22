package com.LibraryManagement.exception;

public class MemberDAOException extends RuntimeException{
	
	public MemberDAOException(String message) {
        super(message);
    }

    public MemberDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
