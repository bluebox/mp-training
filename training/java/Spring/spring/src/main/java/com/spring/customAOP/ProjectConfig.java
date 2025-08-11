package com.spring.customAOP;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages="com.spring.customAOP")
@EnableAspectJAutoProxy
public class ProjectConfig {
}
