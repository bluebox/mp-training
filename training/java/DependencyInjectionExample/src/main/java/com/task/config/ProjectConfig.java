package com.task.config;

import com.task.beans.Person;
import com.task.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"com.task.implementation",
            "com.task.services"})
@ComponentScan(basePackageClasses = {com.task.beans.Vehicle.class,
        com.task.beans.Person.class})
public class ProjectConfig {

}
