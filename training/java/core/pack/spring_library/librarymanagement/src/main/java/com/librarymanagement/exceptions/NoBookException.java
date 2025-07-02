package com.librarymanagement.exceptions;

public class NoBookException extends RuntimeException {

    public NoBookException(String message) {
        super(message);
    }
}