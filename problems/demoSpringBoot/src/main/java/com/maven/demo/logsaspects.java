package com.maven.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class logsaspects {
     
//	@Before("execution(* com.maven.demo.Person.getName(..))")
//	public void log() {
//		System.out.println("Person name field accessing method before");
//	}
//	
//	
//	@After("execution(* com.maven.demo.Person.getName(..))")//* in place for all the methods or all the classes
//	public void log1() {
//		System.out.println("Person name field accessing method after");
//	}
	
	@Pointcut("execution(* com.maven.demo.*.getName(..))")
	public void shortfunc() {};

	
	@Pointcut("within(com.maven.demo.Person )") // for all the methods associated in a person class are binded
	public void shortfunc1() {};
	
	
	@Pointcut("@within(org.springframework.stereotype.Component)") // for all the classes annotated with the @Component annotation
	public void shortfunc2() {};
	
	
	@Pointcut("@Target(org.springframework.stereotype.Component)") // for all the classes object created phase where the class is annotated with the @Component annotation
	public void shortfunc3() {};
	
	
	@Pointcut("Target(com.maven.demo.Person)") // for all the classes object created phase where the class is Person
	public void shortfunc4() {};
	
	//@Annotation used for the function with the annotations ex:requestmapping,getMappingand all the stuff 
	
	// using pointcut in the Around
	@Around("shortfunc2()")
	public Object log2(ProceedingJoinPoint joinpoint) throws Throwable {
		System.out.println("Person name field accessing method before in around in pointcut");
		Object result=joinpoint.proceed();
		System.out.println("Person name field accessing method after in around in pointcut");
		return result;
	}
	
	
	
	
	
}
