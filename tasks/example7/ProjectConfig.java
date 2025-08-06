package com.dom.Springbasic.example7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.dom.Springbasic.example7")
public class ProjectConfig {
	@Bean
	Vehicle vehicle1() {
		var veh=new Vehicle();
		veh.setName("Audi");
		return veh;
		
	}
	@Bean
	Vehicle vehicle2() {
		var veh=new Vehicle();
		veh.setName("Honda");
		return veh;
		
	}
	@Bean
	Vehicle vehicle3() {
		var veh=new Vehicle();
		veh.setName("ferari");
		return veh;
		
	}

}
