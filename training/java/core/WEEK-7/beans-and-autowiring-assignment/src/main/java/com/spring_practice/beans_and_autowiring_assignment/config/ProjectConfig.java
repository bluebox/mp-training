package com.spring_practice.beans_and_autowiring_assignment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring_practice.beans_and_autowiring_assignment.services.VehicleServices;

@Configuration
@ComponentScan(basePackages = {"com.spring_practice.beans_and_autowiring_assignment.pojos","com.spring_practice.beans_and_autowiring_assignment.interfaces.implementations"},
basePackageClasses = VehicleServices.class)
public class ProjectConfig {
	
}
