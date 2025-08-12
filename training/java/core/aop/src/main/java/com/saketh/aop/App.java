package com.saketh.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.saketh.BO.AgeValidator;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(ProjectConfig.class);
        AgeValidator ageValidator=ctx.getBean(AgeValidator.class);
//        ageValidator.validator();
        try {
			Boolean flag=ageValidator.validate(17);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
}
