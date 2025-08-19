package com.lms.Exceptions;


public class MemberDaoException extends RuntimeException {
 public MemberDaoException(String message) {
     super(message);
 }

 public MemberDaoException(String message, Throwable cause) {
     super(message, cause);
 }
}

