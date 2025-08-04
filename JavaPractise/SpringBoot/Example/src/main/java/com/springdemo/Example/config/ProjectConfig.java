package com.springdemo.Example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.springdemo.Example.beans")
public class ProjectConfig {

}
