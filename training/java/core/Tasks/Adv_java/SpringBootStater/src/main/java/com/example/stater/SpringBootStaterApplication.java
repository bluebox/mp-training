package com.example.stater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.stater.model.Alien;

@SpringBootApplication
public class SpringBootStaterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context  =SpringApplication.run(SpringBootStaterApplication.class, args);
		Alien alien1 = context.getBean(Alien.class);
		Alien alien2 = context.getBean(Alien.class);
		//System.out.println(alien.toString());
		alien1.show();
		alien2.show();
	}

}
