package com.library.app.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionError {
	
	 @ExceptionHandler(Exception.class)
	    public ModelAndView exceptionHandler(Exception exception){
	        ModelAndView errorPage = new ModelAndView();
	        errorPage.setViewName("error");
	        errorPage.addObject("errormsg", exception.getMessage());
	        return errorPage;
	    }
}
