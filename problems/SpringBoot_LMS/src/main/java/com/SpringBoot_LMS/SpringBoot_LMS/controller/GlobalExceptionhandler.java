package com.SpringBoot_LMS.SpringBoot_LMS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



import lombok.extern.slf4j.Slf4j;


@Slf4j
@ControllerAdvice
public class GlobalExceptionhandler {
	@ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
          return ResponseEntity.status(500).body("error occured with message :"+exception.getMessage());
    }
}
