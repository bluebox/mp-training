package com.product.Exceptions;

public class CategoryRequestDatabaseOperationException extends Exception{
    private static final long serialVersionUID = -8062764334607268554L;

    public CategoryRequestDatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryRequestDatabaseOperationException(String message) {
        super(message);
    }
}
