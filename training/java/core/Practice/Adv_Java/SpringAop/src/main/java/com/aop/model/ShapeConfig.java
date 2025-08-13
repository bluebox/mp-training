package com.aop.model;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.aop")
public class ShapeConfig {

	@Bean
	public Triangle getTriangle() {
		Triangle triangle=new Triangle();
		triangle.setShapename("triangle1");
		return triangle;
	}
	
	@Bean
	public Circle getCircle() {
		Circle circle=new Circle();
		circle.setShapename("circle1");
		return circle;
	}
	
	@Bean
	public Shapes getShape() {
		Shapes shape=new Shapes();
		return shape;
	}
	
}
