package com.example.inversio_control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleConfig {

	
	@Bean
	Vehicle vehicle1()
	{
		var veh=new Vehicle();
		veh.setName("thar");
		return veh;
	}
	
	@Bean
	Vehicle vehicle2()
	{
		var veh=new Vehicle();
		veh.setName("Bus");
		return veh;
	}
	
	@Bean
	Vehicle vehicle3()
	{
		var veh=new Vehicle();
		veh.setName("Van");
		return veh;
	}
	
	
	
}
