package com.lms.web.exceptions;

public class DAOException extends RuntimeException {
	public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
