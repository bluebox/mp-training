package com.example.springCore.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.example.springCore.annoatations.CustomAnnotations.LogExecutionTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class VehicleAspects {
	
	@Before("execution(* com.example.springCore.service.VehicleServices.*(..))")
	public void beforeVehicleService(JoinPoint joinPoint) {
		System.out.println("Before executing vehicle service method"+"Method Name: "+ joinPoint.getSignature().getName()+
		"\nclass: "+joinPoint.getSignature().getDeclaringTypeName()+"\nPackage: "+joinPoint.getSignature().getDeclaringTypeName().substring(0, joinPoint.getSignature().getDeclaringTypeName().lastIndexOf("."))+"\nTarget : "+joinPoint.getTarget()+"\nProxy name :"+joinPoint.getThis());
	}
	
	@After("execution(* com.example.springCore.service.VehicleServices.*(..))")
	public void afterVehicleService() {
		System.out.println("After executing vehicle service method");
			}
	
	@AfterReturning(pointcut=("execution(* com.example.springCore.Service.*.*(..))"),returning="result")
	public void returningResult(JoinPoint jp,Object result) {
		System.out.println("Result of the method :"+jp.getSignature().getName()+" result: "+result);
	}
	
	@	AfterThrowing(pointcut=("execution(* com.example.springCore.service.*.*(..))"),throwing="ex")
	public void throwingException(JoinPoint jp,Throwable ex) {
		System.out.println("Exception caught in method:"+jp.getSignature().getName()+" Exception: "+ex.getMessage());
		
	}
	
	@Around("execution(* com.example.springCore.service.VehicleServices.*(..))")
	public void PerformanceOfMethods(ProceedingJoinPoint p) {
		String methodName=p.getSignature().getName();
		System.out.println("Before method call :"+methodName);
		long startTime=System.currentTimeMillis();
		Object result=null;
			try {
				result=p.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			long endTime=System.currentTimeMillis();
			System.out.println("Method sucessfully executed and returned Value"+result);
			System.out.println("Total time taken by method:"+p.getSignature().getName()+"Time :"+(endTime-startTime));		
		
	}
	
	@Before("annotation(LogExecutionTime)")
	public void logExecutionTime(JoinPoint joinPoint) {
		System.out.println("LogExecutionTime annotation is applied on method: " + joinPoint.getSignature().getName());
			}
	
}

