package com.example.springbootexample.demospringboot;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionhandler(Exception exception) {
		ModelAndView errorPage=new ModelAndView();
		errorPage.setViewName("error");
		errorPage.addObject("errormsg",exception.getMessage());
		return errorPage;
		
	}
	
	

}
