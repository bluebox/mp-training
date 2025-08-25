//package com.EventManagement.EMS_Backend.Exceptions;
//
//
//
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import lombok.Data;
//
// 
//
//@ControllerAdvice
//public class ErrorHandle {
//	
//	 @ExceptionHandler(MethodArgumentNotValidException.class)
//	    public ResponseEntity<String> handleValidateExceptions(MethodArgumentNotValidException ex) {
//	        System.out.println("this is from exceptions");
//	        return  ResponseEntity.badRequest().body("validations failed please enter correct details");
//	    }
//	 @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//	    public ResponseEntity<String> handleValidateExceptions(SQLIntegrityConstraintViolationException ex) {
//	        System.out.println("this is from exceptions");
//
//	        return  ResponseEntity.badRequest().body("duplicate entry");
//	    }
//	 
//	 @ExceptionHandler(EventnotRegistered.class)
//	    public ResponseEntity<String> handleEventaccessExceptions(EventnotRegistered ex) {
//	        System.out.println("this is from exceptions");
//
//	        return  ResponseEntity.badRequest().body("you are not registered for this event");
//	    }
//	 
//	 @ExceptionHandler(EmptyResultDataAccessException.class)
//	    public ResponseEntity<String> handleEmptydataExceptions(EmptyResultDataAccessException ex) {
//	        System.out.println("this is from exceptions");
//
//	        return  ResponseEntity.badRequest().body("invalid data ,data doesnt matches.Please enter correct data");
//	    }
//	 
//
//	 
//
// @ExceptionHandler(Exception.class)
// public ResponseEntity<String> handleExceptions(Exception ex) {
//	 		
//		return ResponseEntity.badRequest().body("error occured"+ex.getMessage());
// }
//}
//
