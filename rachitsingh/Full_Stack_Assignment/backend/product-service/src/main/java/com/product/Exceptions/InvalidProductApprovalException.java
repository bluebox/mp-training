package com.product.Exceptions;

public class InvalidProductApprovalException extends Exception {
	private static final long serialVersionUID = -3497691215767934394L;

	public InvalidProductApprovalException(String message) {
		super(message);
	}
}
