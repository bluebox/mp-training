package com.karthik.assign.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"com.karthik.assign.implementations","com.karthik.assign.services"})
@ComponentScan(basePackageClasses= {com.karthik.assign.beans.Person.class,com.karthik.assign.beans.Vehicle.class})
public class Config {
	
}
