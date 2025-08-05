package dev.tulasidhar.learn_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class BeanConfig {

	@Bean
	public Human humanBean() {
		return new Human();
	}
	
	@Bean
	@Primary
	public Human otherHumanBean() {
		System.out.println("Primary Human bean is called");
		return new Human();
	}
	@Bean("fastTurtle")
	public Turtle turtleBean() {
		return new Turtle("Fast");
	}
	
	@Bean("slowTurtle")
	public Turtle turtleBean1() {
		return new Turtle("slow");
	}
	
	
	
}
