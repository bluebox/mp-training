package com.springcore.springdemo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages = "com.springcore.springdemo1")
public class VehicleConfig {
	 @Bean(name="audiVehicle")
	    Vehicle vehicle1() {
	        var veh = new Vehicle();
	        veh.setName("Audi");
	        return veh;
	    }

	    @Bean(name="hondaVehicle")
	    Vehicle vehicle2() {
	        var veh = new Vehicle();
	        veh.setName("Honda");
	        return veh;
	    }

	    @Bean(name="ferrariVehicle")
	    Vehicle vehicle3() {
	        var veh = new Vehicle();
	        veh.setName("Ferrari");
	        return veh;
	    }
}
