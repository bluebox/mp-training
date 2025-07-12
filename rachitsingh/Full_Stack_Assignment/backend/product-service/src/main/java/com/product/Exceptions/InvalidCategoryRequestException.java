package com.product.Exceptions;

public class InvalidCategoryRequestException extends Exception{
	private static final long serialVersionUID = -8794309532565406136L;

    public InvalidCategoryRequestException(String message) {
        super(message);
    }
}
