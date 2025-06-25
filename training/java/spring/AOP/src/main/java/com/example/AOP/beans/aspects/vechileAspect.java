package com.example.AOP.beans.aspects;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class vechileAspect {
	
	@Before("execution(* move(..)) && args(isStarted)")
	public void beforeStart(JoinPoint joinPoint,boolean isStarted)
	{
	    System.out.println("Method: " + joinPoint.getSignature());
	    Object args[]=joinPoint.getArgs();
	    
	    System.out.println(isStarted);
//	    if(isStarted==false)
//	    {
//	    	System.out.println("Its false");
//	    	throw new RuntimeException("Hey Bhagavan kay kar raye??");
//	    }
	    
		System.out.println("Hey manoj Its Aspect MAN");
	}
	
	@Around("execution(* play*(..))")
	public void aroundPlayMusic(ProceedingJoinPoint joinPoint) throws Throwable
	{
		System.out.println("HAHA");
		joinPoint.proceed();
	}
	

}
