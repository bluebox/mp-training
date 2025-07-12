package com.product.Exceptions;

public class InvalidProductRequestException extends Exception{
	private static final long serialVersionUID = -5794309532565406136L;

	public InvalidProductRequestException (String message) {
		super(message);
	}
}
