package com.librarymanagement.controller;



import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.librarymanagement.exceptions.DuplicateEntryException;
import com.librarymanagement.exceptions.GeneralException;
import com.librarymanagement.exceptions.IssuedBookException;
import com.librarymanagement.exceptions.NoBookException;
import com.librarymanagement.exceptions.NoMemberException;


@ControllerAdvice
public class ExceptionController {

    
    @ExceptionHandler(NoBookException.class)
    public ResponseEntity<String> handleBookNotFound(NoBookException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoMemberException.class)
    public ResponseEntity<String> handleMemberNotFound(NoMemberException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(IssuedBookException.class)
    public ResponseEntity<String> handleMemberNotFound(IssuedBookException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(DuplicateEntryException.class)
    public ResponseEntity<String> handleMemberNotFound(DuplicateEntryException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
            .stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<String> handleRuntimeException(GeneralException ex) {
        return new ResponseEntity<>("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Generic fallback for any other RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>("Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Optional: handle all other exceptions (checked exceptions too)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
