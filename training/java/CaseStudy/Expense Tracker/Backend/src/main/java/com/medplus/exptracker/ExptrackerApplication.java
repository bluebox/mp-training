package com.medplus.exptracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class ExptrackerApplication {
	public static void main(String[] args) {               
		SpringApplication.run(ExptrackerApplication.class, args);
	}

}
