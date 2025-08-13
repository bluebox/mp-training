package com.example.DemoAop.Aopexample;

import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;





@Component
@Aspect
public class Example {
	
	@Before("execution(* com.example.DemoAop.Aopexample.*.*(..))")
	public void beforeexp() {
		System.out.println("hi this is before the execution");
	}
	@After("execution(* com.example.DemoAop.Aopexample.*.*(..))")
	public void afteraxp() {
		System.out.println("hi this is after the execution");
	}
	@Around("execution(* com.example.DemoAop.Aopexample.*.*(..))")
	public void aroundexp(ProceedingJoinPoint joinPoint) {
		System.out.println("hi this is before in around section ");
		try {
			joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("hi this is around the execution");
	}
	@AfterReturning("execution(* com.example.DemoAop.Aopexample.*.*(..))")
	public void afterreturn() {
		System.out.println("hi this is afterreturn the execution");
	}
	@AfterThrowing("execution(* com.example.DemoAop.Aopexample.*.*(..))")
	public void afterthrowing() {
		System.out.println("this is afterthrowing the execution");
	}
	@Before("@within(org.springframework.stereotype.Component)")
	public void beforewithin() {
		System.out.println("this is before from within execution");
	}
	@Before("@target(org.springframework.stereotype.Component)")
	public void beforetarget() {
		System.out.println("this is before from target");
	}
}






