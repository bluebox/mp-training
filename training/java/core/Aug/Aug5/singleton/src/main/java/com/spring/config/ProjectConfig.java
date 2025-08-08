package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.spring.beans", "com.spring.implementation", "com.spring.services" })
public class ProjectConfig {

}
