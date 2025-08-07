package dev.tulasidhar.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class Helper {
	
	@Pointcut("execution(* dev.tulasidhar.beans.Heart.*(..))")
	public void heartMethods() {};
	
	
	@Before("heartMethods()")
	public void log() {
		System.out.println("This will log about things before calling bussiness logic");
	}
}
