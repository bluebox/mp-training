package com.product.Exceptions;

public class ProductRequestDatabaseOperationException extends Exception {
	private static final long serialVersionUID = -4062764334607268554L;

	public ProductRequestDatabaseOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductRequestDatabaseOperationException(String message) {
		super(message);
	}
}
