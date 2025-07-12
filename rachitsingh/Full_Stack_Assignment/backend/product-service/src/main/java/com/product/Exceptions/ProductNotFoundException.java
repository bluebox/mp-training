package com.product.Exceptions;

public class ProductNotFoundException extends Exception {
	private static final long serialVersionUID = -252651831258123392L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
