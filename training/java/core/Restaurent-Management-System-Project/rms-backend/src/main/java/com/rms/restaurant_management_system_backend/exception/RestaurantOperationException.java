package com.rms.restaurant_management_system_backend.exception;

public class RestaurantOperationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RestaurantOperationException(String message) {
		super(message);
	}

}
