package com.librarymanagement.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



import java.time.Duration;
import java.time.Instant;

//import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Before;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;


@Component
@Aspect
public class AspectShower {
	 private Logger logger = Logger.getLogger(AspectShower.class.getName());
	 
	 @Around("@annotation(com.librarymanagement.aspects.Duration)")
	 public Object giveTimeElapses(ProceedingJoinPoint joinPoint) throws Throwable  {
		 logger.info(joinPoint.toString() + "\n---------------------------- method execution start----------------------");
		 
		 Instant Start =Instant.now();
		 Object obj=joinPoint.proceed();
		 Instant finish=Instant.now();
		 Duration timeElapsed=Duration.between(Start, finish);
		 logger.info("Time took to execute the method: "+timeElapsed);
		 logger.info(joinPoint.getSignature().toString()+" method execution end");
		 return obj;
		 
}
	 
}
