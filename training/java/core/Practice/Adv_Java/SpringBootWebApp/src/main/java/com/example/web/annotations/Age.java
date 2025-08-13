package com.example.web.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
@Constraint(validatedBy = AgeValidation.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Age {
	String message() default "Age Must be Above or equal to 18";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
