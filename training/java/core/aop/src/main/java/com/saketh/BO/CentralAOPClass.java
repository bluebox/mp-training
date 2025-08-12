package com.saketh.BO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CentralAOPClass {
	@Pointcut("execution(* AgeValidator.validate(..))")
	public void v() {}
	
	@Before("v()")
	public void beforeAdvice(JoinPoint jp) {
		System.out.println("This is before advice and executed before validate method");
			serve();
	
	}
	
	@After("v()")
	public void afterAdvice(JoinPoint jp) {
		System.out.println("This is After advice");
	}
	
	
	@Around("v()")
	public void aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("Before Proceed method in around");
		jp.proceed();
		System.out.println("After proceed methid in around");
	}
		
	@AfterThrowing(pointcut="v()",throwing="e")
	public void exceprionoccured(JoinPoint jp,Exception e) {
		System.out.println("This is after exceptio is thrown ");
		System.out.println(e.getMessage());
	}
	@AfterReturning(pointcut="v()",returning="result")
	public void afterreturnongmethod(JoinPoint jp,Boolean result) {
		System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
	}
	
	@Around("@annotation(CustomAnnotation)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
	    long start = System.currentTimeMillis();

	    Object proceed = joinPoint.proceed();

	    long executionTime = System.currentTimeMillis() - start;

	    System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}
	@CustomAnnotation
	public void serve(){
		System.out.println("This is custom annoatation method");
	}
	
}
