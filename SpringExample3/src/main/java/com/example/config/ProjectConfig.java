package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.beans.Vehicle;

@Component
public class ProjectConfig {

	@Bean(name = "bmwvehicle")
	Vehicle vehicle1() {
		Vehicle veh = new Vehicle();
		veh.setName("bmw..");
		return veh;

	}
	

	@Bean(name="ferarivehicle")
	Vehicle vehicle2() {
		Vehicle veh=new Vehicle();
		veh.setName("ferari..");
		return veh;
		
	}
}
