package com.product.Exceptions;

public class ProductDatabaseOperationException extends Exception {
	private static final long serialVersionUID = -4062764334607268554L;

	public ProductDatabaseOperationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductDatabaseOperationException(String message) {
		super(message);
	}
}
