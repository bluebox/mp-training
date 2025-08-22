package com.library.Exception;

public class IssueReturnDAOImplementation extends RuntimeException{
	
	public IssueReturnDAOImplementation(String message) {
        super(message);
    }

    public IssueReturnDAOImplementation(String message, Throwable cause) {
        super(message, cause);
    }
}
