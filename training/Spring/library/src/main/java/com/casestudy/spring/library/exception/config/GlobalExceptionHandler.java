package com.casestudy.spring.library.exception.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String redirect(Exception e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "error";
	}
}
