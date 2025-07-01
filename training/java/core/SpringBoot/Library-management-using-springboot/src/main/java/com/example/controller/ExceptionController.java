package com.example.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception e) {
		ModelAndView errorPage=new ModelAndView();
		errorPage.setViewName("exception");
		String s=e.getMessage().toLowerCase();
		if(s.contains("sql")) {
			String s1=(s.contains("insert"))?"insertion":s.contains("update")?"updation":s.contains("delete")?"deletion":"selection";
			errorPage.addObject("error","SQL exception occured during "+s1);
		}
		return errorPage;
	}
}
