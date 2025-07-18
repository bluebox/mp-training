package com.product.Exceptions;

public class CategoryDatabaseOperationException extends Exception{
	
	private static final long serialVersionUID = -8062764334607268554L;

    public CategoryDatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryDatabaseOperationException(String message) {
        super(message);
}
}