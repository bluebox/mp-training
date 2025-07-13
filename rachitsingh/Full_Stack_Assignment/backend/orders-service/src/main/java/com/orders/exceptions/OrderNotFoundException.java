package com.orders.exceptions;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = -1610107538871850895L;

	public OrderNotFoundException(String message) {
		super(message);
	}
}
