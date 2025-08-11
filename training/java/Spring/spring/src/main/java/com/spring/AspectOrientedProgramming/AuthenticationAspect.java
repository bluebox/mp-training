package com.spring.AOP;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuthenticationAspect {

	@Pointcut("within(com.spring.AOP..*)")
	public void authenticationPointcut() {
		
	}
	
	@Pointcut("within(com.spring.AOP.ShoppingCart)")
	public void authorizationPointcut() {
		
	}
	
	@Before("authenticationPointcut() && authorizationPointcut()")
	public void authenticate() {
		System.out.println("Authenticating the request");
	}
}
