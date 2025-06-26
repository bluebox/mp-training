package com.nameBeanPrimary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfig {
	
	@Bean(name="audiVehicle")
	Vehicle vehicl1() {
		var veh = new Vehicle();
		veh.setName("audi premium");
		return veh;
	}
	
	@Primary
	@Bean(value="tataVehicle")
	Vehicle vehicl2() {
		var veh = new Vehicle();
		veh.setName("tata safari");
		return veh;
	}
	
	@Bean("mahendraVehicle")
	Vehicle vehicl3() {
		var veh = new Vehicle();
		veh.setName("thar");
		return veh;
	}
}
