package com.loanmanagement.Exceptions;

public class InvalidPaymentException extends RuntimeException{
 public InvalidPaymentException(String msg) {
	 super(msg);
 }
}
