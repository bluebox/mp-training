package com.lms.Exceptions;



public class BookDaoException extends RuntimeException {
 public BookDaoException(String message) {
     super(message);
 }

 public BookDaoException(String message, Throwable cause) {
     super(message, cause);
 }
}

