package com.exampletwo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@Configuration
@ComponentScan(basePackages="beanSample")
public class Configurate {
	
	@Bean
	Ferrari ferrari(){
		Ferrari ferrari=new Ferrari();
		return ferrari;
	}
	
	@Bean
	Ferrari ferrari1() {
		Ferrari ferrari1=new Ferrari();
		return ferrari1;
		
	}
	@Bean
	@Primary
	BMW bmw() {
		BMW bmw=new BMW();
		return bmw;
	}
}
