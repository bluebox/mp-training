package dev.tulasidhar.spring_boot_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SpringBootAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootAppApplication.class, args);
	}
}
