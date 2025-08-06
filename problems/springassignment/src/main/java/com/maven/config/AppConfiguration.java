package com.maven.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.maven.beans","com.maven.Implementation","com.maven.Services"})
public class AppConfiguration {

}
