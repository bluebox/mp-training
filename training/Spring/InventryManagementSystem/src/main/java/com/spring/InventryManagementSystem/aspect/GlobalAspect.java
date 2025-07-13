package com.spring.InventryManagementSystem.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class GlobalAspect {

	@Around("execution(* com.spring.InventoryManagementSystem.*.*(..))")
	public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {

		Object[] args = joinPoint.getArgs();

		log.info("running " + joinPoint.getSignature().getDeclaringType().getSimpleName() + "."
				+ joinPoint.getSignature().getDeclaringType().getSimpleName() + " | Args: " + Arrays.toString(args));

		Object result = joinPoint.proceed();

		log.info("completed " + joinPoint.getSignature().getDeclaringType().getSimpleName() + "."
				+ joinPoint.getSignature().getDeclaringType().getSimpleName() + " | Returned: " + result);

		return result;
	}

}

