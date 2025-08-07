package com.spring_practice.autowiring_demonstration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.spring_practice.autowiring_demonstration.beans")
public class ProjectConfig {
}
