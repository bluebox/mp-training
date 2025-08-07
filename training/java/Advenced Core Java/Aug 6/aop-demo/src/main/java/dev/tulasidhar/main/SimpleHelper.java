package dev.tulasidhar.main;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class SimpleHelper {
	
	
	
	
	
	@Before("execution(* dev.tulasidhar.main.Box.*(..))")
	public void logInfo() {
		System.out.println("This should be called before the box is opened");
	}
}
