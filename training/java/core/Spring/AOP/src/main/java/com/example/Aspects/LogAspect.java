package com.example.Aspects;

import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect{
	@Before("execution(* com.example.dao.*.*(..))")
	public void beforeAspect() {
		System.out.println("@Before everywhere : Before The method is started");
	}
	@Before("execution(* com.example.dao.Person.getName(..))")
	public void beforeAspect1() {
		System.out.println("@Before : Before method for get method");
	}
	@After("execution(* com.example.dao.Person.getName(..))")
	public void AfterAspect() {
		System.out.println("@After : After Get method ended");
	}
	@AfterThrowing("execution(* com.example.dao.Person.toString(..))")
	public void exception() {
		System.out.println("@AfterThrowing : An exception occurred");
	}
	@AfterReturning("execution(* com.example.dao.*.*(..))")
	public void afterReturning() {
		System.out.println("@AfterReturning : After the method is executed");
	}
	@Around("execution(* com.example.dao.*.*(..))")
	public void around(ProceedingJoinPoint j) throws Throwable {
		 Instant initial = Instant.now();
		 System.out.println("Around : Around execution started");
		 j.proceed();
		 Instant final1=Instant.now();
		 System.out.println("Around : Around execution ended after time "+Duration.between(initial, final1).toMillis()+" ms");
	}
}
