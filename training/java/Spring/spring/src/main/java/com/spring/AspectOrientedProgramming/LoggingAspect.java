package com.spring.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	@Before("execution( * com.spring.AOP.ShoppingCart.checkout())")
	public void beforeLogger() {
		System.out.println("Before Logger");
	}
	
	@After("execution( * com.spring.AOP.ShoppingCart.checkout2(..))")
	public void afterLogger(JoinPoint jp) {
		System.out.println(jp.getSignature());
		System.out.println("After Logger - " + jp.getArgs()[0].toString());
	}
}
