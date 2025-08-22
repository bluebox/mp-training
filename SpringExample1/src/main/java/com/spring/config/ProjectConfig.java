package com.spring.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.beans.Vehicle;

@Configuration
public class ProjectConfig {
	
		@Bean
	    Vehicle vehicle1() {
	        var veh = new Vehicle();
	        veh.setName("benz...");
	        return veh;
	    }
		
		@Bean
	    Vehicle vehicle2() {
	        var veh = new Vehicle();
	        veh.setName("bmw...");
	        return veh;
	    }
		@Bean
	    Vehicle vehicle3() {
	        var veh = new Vehicle();
	        veh.setName("ferari...");
	        return veh;
	    }
	
}
