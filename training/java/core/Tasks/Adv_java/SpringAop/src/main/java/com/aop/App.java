package com.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.aop.model.ShapeConfig;
import com.aop.model.Shapes;

public class App 
{
    public static void main( String[] args )
    {
       ApplicationContext context = new AnnotationConfigApplicationContext(ShapeConfig.class);
       Shapes s1=context.getBean(Shapes.class);
       System.out.println(s1.getCircle().getShapename());
       
       
       System.out.println(s1.getTriangle().getShapename());
       s1.getTriangle().setShapename("triangle2");
       System.out.println(s1.getTriangle().getShapename());
    }
}
