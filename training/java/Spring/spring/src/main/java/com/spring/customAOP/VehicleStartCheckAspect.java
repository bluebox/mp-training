package com.spring.customAOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class VehicleStartCheckAspect {
	
	@Before("execution(* com.spring.customAOP.VehicleService.*(..)) && args(vehicleStarted,..)")
	public void checkVehicleStarted(JoinPoint joinPoint, boolean vehicleStarted) throws Throwable {
		
		if (!vehicleStarted) {
			throw new RuntimeException("Vehicle not started");
		}
	}
}
