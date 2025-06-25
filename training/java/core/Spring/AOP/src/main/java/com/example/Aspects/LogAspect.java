package com.example.Aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect{
	@Before("execution(* com.example.dao.*.*(..))")
	public void beforeAspect() {
		System.out.println("The method is started");
	}
}
