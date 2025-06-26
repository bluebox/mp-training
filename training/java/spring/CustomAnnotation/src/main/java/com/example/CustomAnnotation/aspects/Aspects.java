package com.example.CustomAnnotation.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Aspects {
	
	@Before("@annotation(com.example.CustomAnnotation.annotations.LogAspect)")
	public void beforePlay()
	{
		System.out.println("Hey music Ahhhh");
	}

}
