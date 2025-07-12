package com.orders.Exceptions;

public class OrderDatabaseOperationException extends Exception {
	private static final long serialVersionUID = -3513194069519689778L;

	public OrderDatabaseOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public OrderDatabaseOperationException(String message) {
		super(message);
	}
}
