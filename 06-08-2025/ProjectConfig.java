package com.example17;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackages = { "com.example17"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}