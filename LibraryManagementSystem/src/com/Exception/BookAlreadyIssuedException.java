package com.Exception;

public class BookAlreadyIssuedException extends Exception {
    public BookAlreadyIssuedException(String message) {
        super(message);
    }

    public BookAlreadyIssuedException(String message, Throwable cause) {
        super(message, cause);
    }
}