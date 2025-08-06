package com.example.springCore.annoatations;
import java.lang.annotation.*;
public class CustomAnnotations {
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface LogExecutionTime {
	}
}
