package com.dom.Springbasic.example8;


import com.dom.Springbasic.example8.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/*
Spring @Configuration annotation is part of the spring core framework.
Spring Configuration annotation indicates that the class has @Bean definition
methods. So Spring container can process the class and generate Spring Beans
to be used in the application.

To tell Spring it needs to search for classes annotated
with stereotype annotations, we use the @ComponentScan annotation over the
configuration class.
* */
@Configuration
@ComponentScan(basePackages = {"com.dom.Springbasic.example8"})
@ComponentScan(basePackageClasses = {com.dom.Springbasic.example8.Vehicle.class,
		com.dom.Springbasic.example8.Person.class})
public class ProjectConfig {

}