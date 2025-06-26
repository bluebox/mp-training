package com.spring.asp.aspect;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Crosscutting {

	private Logger logger = Logger.getLogger(Crosscutting.class.getName());

	@Before("execution(* com.spring.asp.beans.*.*(..))")
	public void checkingRun(JoinPoint joinpoint) {
		logger.info("is starting" + joinpoint.toString());
	}

	@After("execution(* com.spring.asp.beans.*.*(..))")
	public void checkingRunisDone(JoinPoint joinpoint) {
		logger.info("Is ending " + joinpoint.toString());
	}

	@AfterThrowing("execution(* com.spring.asp.beans.*.*(..))")
	public void throwed(JoinPoint joinPoint) {
		logger.log(Level.SEVERE, "Error Occured at " + joinPoint.getSignature().toString());
	}

	@Around("execution(* com.spring.asp.beans.*.*(..))")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("By Around .");
		Instant start = Instant.now();
		joinPoint.proceed();
		Instant stop = Instant.now();
		logger.info("By Around : " + Duration.between(start, stop).toMillis());
	}

	@Around("@annotation(com.spring.asp.interfaces.CustomAnnotation)")
	public void Custom(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("Custom Annotation");
		joinPoint.proceed();
	}

	@AfterReturning(value = "execution(* com.spring.asp.beans.*.*(..))",returning="result")
	public void returnedString(JoinPoint joinPoint,String result) throws Throwable {
		logger.info("By Return"+ result);
	}

}
