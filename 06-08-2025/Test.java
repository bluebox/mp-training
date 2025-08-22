package com.example15;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test{
	public static void main(String args[]) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ProjectConfig.class);
    Region region = ctx.getBean(Region.class);
    City city = ctx.getBean(City.class);
    city.hashCode();
    region.getCityInstance();
    ctx.close();
}
}