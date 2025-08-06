package com.example.springcorePractice.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.springcorePractice.model.Car;
import com.example.springcorePractice.model.Engine;


@Configuration
public class AppConfig {

    @Bean
    public Engine engine() {
        return new Engine();
    }

    @Bean
    public Car car() {
        return new Car(engine()); 
    }
    
 @Bean 
 @Primary
 public Engine e1() {
		return new Engine(); 
	}
 @Bean
 public Engine e2() {
		return new Engine(); 
	}
	
}
