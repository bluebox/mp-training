package com.lms.Exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(BookDaoException.class)
 public ResponseEntity<Object> handleBookDAOException(BookDaoException ex) {
     return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
 }

 @ExceptionHandler(MemberDaoException.class)
 public ResponseEntity<Object> handleMemberDAOException(MemberDaoException ex) {
     return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
 }

 @ExceptionHandler(IssueReturnDaoException.class)
 public ResponseEntity<Object> handleIssueReturnDAOException(IssueReturnDaoException ex) {
     return buildResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
 }

 // Catch-all handler (optional)
 @ExceptionHandler(Exception.class)
 public ResponseEntity<Object> handleGeneralException(Exception ex) {
     return buildResponse("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
 }

 private ResponseEntity<Object> buildResponse(String message, HttpStatus status) {
     Map<String, Object> body = new HashMap<>();
     body.put("timestamp", LocalDateTime.now());
     body.put("status", status.value());
     body.put("error", status.getReasonPhrase());
     body.put("message", message);

     return new ResponseEntity<>(body, status);
 }
}

