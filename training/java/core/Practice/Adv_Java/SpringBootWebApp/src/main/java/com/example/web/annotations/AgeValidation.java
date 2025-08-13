package com.example.web.annotations;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeValidation implements ConstraintValidator<Age, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.trim().isEmpty()) {
	        return false; 
	    }
		return Period.between(LocalDate.parse(value), LocalDate.now()).getYears()>=18;
	}
}
