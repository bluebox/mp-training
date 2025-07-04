package com.casestudy.spring.library.exception.config;

import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(2)
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String redirect(Exception e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "error";
	}
}
