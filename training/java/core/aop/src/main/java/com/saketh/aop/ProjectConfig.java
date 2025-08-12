package com.saketh.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.saketh.BO.CentralAOPClass;

@Configuration
@ComponentScan(basePackages="com.saketg.aop,com.saketh.BO")
@EnableAspectJAutoProxy
public class ProjectConfig {

	@Bean
	public CentralAOPClass aop() {
		return new CentralAOPClass();
	}
}
