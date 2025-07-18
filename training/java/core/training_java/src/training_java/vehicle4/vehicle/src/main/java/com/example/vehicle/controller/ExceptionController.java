package com.example.vehicle.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class ExceptionController {
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> sqlExceptionHandler(SQLException sqlExcep) {
		log.error(sqlExcep.getMessage(), sqlExcep);
		return new ResponseEntity<>("Error: There is a Database Exception", HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> excepHandler(Exception exception) {
		log.error(exception.getMessage(),exception);
		return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
