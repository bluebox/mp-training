package com.casestudy.spring.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AspectForAll {

    @Before("execution(* com.casestudy.spring.library.impl..*(..))")
    public void beforeLogging(JoinPoint joinPoint) {
        log.info("Running: {}", joinPoint.getSignature());
    }

    @After("execution(* com.casestudy.spring.library.impl..*(..))")
    public void afterLogging(JoinPoint joinPoint) {
        log.info("Completed: {}", joinPoint.getSignature());
    }
}
