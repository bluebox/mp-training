package com.orders.Exceptions;

public class InvalidOrderException extends Exception {
	private static final long serialVersionUID = -5794309532565406136L;

	public InvalidOrderException(String message) {
		super(message);
	}
}
