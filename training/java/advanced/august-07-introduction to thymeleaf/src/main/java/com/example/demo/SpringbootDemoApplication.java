package com.example.demo;

import java.util.Objects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootDemoApplication {

	public static void main(String[] args) {
		Objects.hash(2,34);
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

}
