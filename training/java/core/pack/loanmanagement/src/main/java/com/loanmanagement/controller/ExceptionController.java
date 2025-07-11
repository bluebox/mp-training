package com.loanmanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.loanmanagement.Exceptions.InvalidIdException;
import com.loanmanagement.Exceptions.InvalidPaymentException;
import com.loanmanagement.Exceptions.NoLoanException;
import com.loanmanagement.Exceptions.NoMemberException;
import com.loanmanagement.Exceptions.GeneralException;



@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(InvalidIdException.class)
    public ResponseEntity<String> handleIdNotFound(InvalidIdException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	//InvalidPaymentException
	@ExceptionHandler(NoMemberException.class)
    public ResponseEntity<String> handleMemberNotFound(NoMemberException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(InvalidPaymentException.class)
    public ResponseEntity<String> handlePaymentNotFound(InvalidPaymentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(NoLoanException.class)
    public ResponseEntity<String> handleLoanNotFound(NoLoanException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	 @ExceptionHandler(GeneralException.class)
	    public ResponseEntity<String> handleRuntimeException(GeneralException ex) {
	        return new ResponseEntity<>("Unexpected error: " + ex.getMessage(),  HttpStatus.NOT_FOUND);
	    }
}
