package com.lms.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.lms.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(" Entering: " + joinPoint.getSignature());
    }

    @AfterReturning(pointcut = "execution(* com.lms.service.*.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println(" Exiting: " + joinPoint.getSignature() + " with result = " + result);
    }
}
