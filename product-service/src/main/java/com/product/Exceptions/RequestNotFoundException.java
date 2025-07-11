package com.product.Exceptions;

public class RequestNotFoundException extends Exception {
	private static final long serialVersionUID = -5794309532565406136L;

	public RequestNotFoundException(String message) {
		super(message);
	}
}
