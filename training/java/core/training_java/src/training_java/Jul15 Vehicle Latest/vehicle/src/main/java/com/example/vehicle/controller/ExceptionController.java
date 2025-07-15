package com.example.vehicle.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice(annotations = Controller.class)
@Slf4j
public class ExceptionController {
	
	@ExceptionHandler(SQLException.class)
	public String sqlExceptionHandler(SQLException sqlExcep) {
		log.error(sqlExcep.getMessage(), sqlExcep);
		return "There is A Data Base Exception";
	}
	
	@ExceptionHandler(Exception.class)
	public String excepHandler(Exception exception) {
		log.error(exception.getMessage());
		return "There is A Exception Occured";
	}
	
	

	
}
