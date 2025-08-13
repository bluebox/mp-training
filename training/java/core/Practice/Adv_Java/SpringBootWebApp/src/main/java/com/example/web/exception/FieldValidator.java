package com.example.web.exception;
  
import java.util.HashMap;
import java.util.Map;  
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice public class FieldValidator {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public String validateFields(MethodArgumentNotValidException e,Model model){ 
		Map<String, String> errorMap = new HashMap<String, String>();
		e.getBindingResult().getFieldErrors().forEach(error->errorMap.put(error.getField(),error.getDefaultMessage())); 
		model.addAttribute("errors", errorMap);
	    model.addAttribute("student", e.getBindingResult().getTarget());

	    return "Home";
	}
}
 