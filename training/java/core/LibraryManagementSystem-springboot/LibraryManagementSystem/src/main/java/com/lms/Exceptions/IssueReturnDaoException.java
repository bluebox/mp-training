package com.lms.Exceptions;



public class IssueReturnDaoException extends RuntimeException {
 public IssueReturnDaoException(String message) {
     super(message);
 }

 public IssueReturnDaoException(String message, Throwable cause) {
     super(message, cause);
 }
}

