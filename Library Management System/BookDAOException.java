package com.LibraryManagement.exception;

public class BookDAOException extends RuntimeException{
	public BookDAOException(String message) {
        super(message);
    }

    public BookDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}