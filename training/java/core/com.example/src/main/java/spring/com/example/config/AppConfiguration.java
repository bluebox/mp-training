package spring.com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"spring.com.example.implementation","spring.com.example.services"})
@ComponentScan(basePackageClasses= {spring.com.example.beans.Person.class,
		        spring.com.example.beans.Vehicle.class})
public class AppConfiguration {


}
