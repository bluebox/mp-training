package com.lms.exceptions;

@SuppressWarnings("serial")
public class DAOException extends Exception {
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

	public DAOException(String string) {
		super(string);
	}
}