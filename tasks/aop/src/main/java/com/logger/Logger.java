package com.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Logger {
	@Before("execution(* com.beans.Student.get*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("Going to setup student profile.");
    }

    @After("execution(* com.beans.Student.get*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("Student profile has been setup.");
    }

    @AfterReturning(pointcut = "execution(* com.beans.Student.get*(..))", returning = "retVal")
    public void afterReturningAdvice(Object retVal) {
        System.out.println("Returning: " + retVal);
    }

    @AfterThrowing(pointcut = "execution(* com.beans.Student.printThrowException(..))", throwing = "ex")
    public void afterThrowingAdvice(IllegalArgumentException ex) {
        System.out.println("There has been an exception: " + ex.toString());
    }
}
