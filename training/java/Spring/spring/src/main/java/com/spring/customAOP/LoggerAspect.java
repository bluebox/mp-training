package com.spring.customAOP;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class LoggerAspect {
	private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

//    @Around("execution(* com.spring.customAOP.VehicleService.*(..))")
//    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
//        logger.info(joinPoint.getSignature().toString() + " method execution start");
//        Instant start = Instant.now();
//        joinPoint.proceed();
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).toMillis();
//        logger.info("Time took to execute the method : "+timeElapsed);
//        logger.info(joinPoint.getSignature().toString() + " method execution end");
//    }

	@Around("@annotation(com.spring.customAOP.LogAspect)")
	public Object logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info(joinPoint.toString() + " method execution start");
		Instant start = Instant.now();
		System.out.println(start);
		Object ret = joinPoint.proceed();
		System.out.println(ret);
		Instant finish = Instant.now();
		System.out.println(finish);
		long timeElapsed = Duration.between(start, finish).toMillis();
		logger.info("Time took to execute the method : " + timeElapsed);
		logger.info(joinPoint.getSignature().toString() + " method execution end");
		return ret;
	}

	@AfterThrowing(value = "execution(* com.spring.customAOP.VehicleService.*(..))", throwing = "ex")
	public void logException(JoinPoint joinPoint, Exception ex) {
		logger.log(Level.SEVERE, joinPoint.getSignature() + " An exception thrown with the help of"
				+ " @AfterThrowing which happened due to : " + ex.getMessage());
	}

	@AfterReturning(value = "execution(* com.spring.customAOP.VehicleService.*(..))", returning = "retVal")
	public void logStatus(JoinPoint joinPoint, Object retVal) {
		System.out.println(retVal);
		logger.log(Level.INFO,
				joinPoint.getSignature() + " Method successfully processed with the status " + retVal.toString());
	}
}
