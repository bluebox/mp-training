package mymaven;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



@Configuration
@ComponentScan(basePackages = "mymaven")
public class AppConfig {

	@Bean
    public MyService myServiceBean() { 
        return new MyService("Hello from Spring Boot!");
    }
	
}