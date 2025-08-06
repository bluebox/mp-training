package com.springboot.Example2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {
		"com.springboot.Example2.beans",
		"com.springboot.Example2.implementation",
		"com.springboot.Example2.services",
		"com.springboot.Example2.interfaces"
})
public class ProjectConfig {

}
