package com.rms.restaurant_management_system_backend.utilities;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

//	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.rms.restaurant_management_system_backend.controller.*.*(..))")
	public void logBeforeController(JoinPoint joinPoint) {
		log.info("controller method called:{} - Params:{}", joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "execution(* com.rms.restaurant_management_system_backend.controller.*.*(..))", returning = "result")
	public void logAfterController(JoinPoint joinPoint, Object result) {
		log.info("controller method finished:{} - Response:{}", joinPoint.getSignature().getName(), result);
	}

	@Before("execution(* com.rms.restaurant_management_system_backend.service.*.*(..))")
	public void logBeforeService(JoinPoint joinPoint) {
		log.info("service method called:{} - Params:{}", joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "execution(* com.rms.restaurant_management_system_backend.service.*.*(..))", returning = "result")
	public void logAfterService(JoinPoint joinPoint, Object result) {
		log.info("service method completed:{} - Result:{}", joinPoint.getSignature().getName(), result);
	}

	@Before("execution(* com.rms.restaurant_management_system_backend.dao.*.*(..))")
	public void logBeforeDAO(JoinPoint joinPoint) {
		log.info("DAO method executed:{} - Params:{}", joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "execution(* com.rms.restaurant_management_system_backend.dao.*.*(..))", returning = "result")
	public void logAfterDAO(JoinPoint joinPoint, Object result) {
		log.info("DAO method completed:{} - Result:{}", joinPoint.getSignature().getName(), result);
	}

	@AfterThrowing(pointcut = "execution(* com.rms.restaurant_management_system_backend.*.*(..))", throwing = "ex")
	public void logException(JoinPoint joinPoint, Throwable ex) {
		log.error("Exception in:{} - Message:{}", joinPoint.getSignature().getName(), ex.getMessage(), ex);
	}
}