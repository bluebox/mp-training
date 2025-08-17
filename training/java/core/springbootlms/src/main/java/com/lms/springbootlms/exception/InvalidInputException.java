package com.lms.springbootlms.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
    public InvalidInputException(String message,Exception e) {
        super(message+e.getMessage());
    }
}
