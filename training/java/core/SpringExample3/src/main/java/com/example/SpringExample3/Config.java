package com.example.SpringExample3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public Vehicle vehicle()
	{
		
		Vehicle veh=new Vehicle();
		System.out.println("Vehicle created");
		veh.setName("Audi");
		return veh;
	}
	
	@Bean
	public Person person(Vehicle veh)
	{
		System.out.println("persom created");
		Person p=new Person();
		p.setName("Tarun");
//		p.setVehicle(vehicle());
		p.setVehicle(veh);
		return p;
	}
	

}
