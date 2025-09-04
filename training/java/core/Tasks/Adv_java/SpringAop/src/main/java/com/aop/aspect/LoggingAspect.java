package com.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import com.aop.model.Triangle;

@Component
@Aspect
@EnableAspectJAutoProxy
public class LoggingAspect {
	
	@Before("execution(* getShapename())")
	public void loggingAdvice() {
		System.out.println("Advice is run and the get name method is called");
	}
	
	@Before("execution(* com.aop.model.Shapes.get*())")
	public void logoutAdvice() {
		System.out.println("Advice 2 is run and get shape is called");
	}
	
	@After("allGetters() && circleMethods()")
	public void validateAdvice(){
		System.out.println("ValidatedCircleGet");
	}
	
	@After("triangleMethods()")
	public void validatingAdvice(JoinPoint joinPoint) {
		System.out.println(joinPoint.toShortString());
		System.out.println(joinPoint.toLongString());
		System.out.println(joinPoint.toString());
		System.out.println(joinPoint.getTarget());
		System.out.println("//");
	}
	
	@Around("allSetters() && triangleMethods()")
	public void triangleUpdatedName(ProceedingJoinPoint jp) {
	    Object target = jp.getTarget();
	    String beforeName = null;
	    String afterName = null;

	    if (target instanceof Triangle) {
	        beforeName = ((Triangle) target).getShapename();
	        System.out.println("Before update: " + beforeName);
	    }

	    try {
	        jp.proceed(); 
	    } catch (Throwable e) {
	        e.printStackTrace();
	    }

	    if (target instanceof Triangle) {
	        afterName = ((Triangle) target).getShapename();
	        System.out.println("After update: " + afterName);
	    }
	}
	
	@AfterReturning("@annotation(com.aop.aspect.Loggable)")
	public void custom() {
		System.out.println("From custom advice");
	}

	@Pointcut("execution(* get*())")
	public void allGetters() {	
	}
	
	@Pointcut("execution(* set*(..))")
	public void allSetters() {
	}
	
	@Pointcut("within(com.aop.model.Circle)")
	public void circleMethods() {	
	}
	
	@Pointcut("within(com.aop.model.Triangle)")
	public void triangleMethods() {
	}
}
