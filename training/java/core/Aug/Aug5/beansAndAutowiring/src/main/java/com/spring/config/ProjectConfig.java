package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.spring.implementation", "com.spring.services", "com.spring.beans" })

public class ProjectConfig {

}
