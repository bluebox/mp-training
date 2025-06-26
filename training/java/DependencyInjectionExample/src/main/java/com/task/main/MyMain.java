package com.task.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.task.beans.Person;
import com.task.beans.Vehicle;
import com.task.config.ProjectConfig;
import com.task.implementation.BoseSpeakers;
import com.task.implementation.MichelinTyres;

public class MyMain {
	
	public static void main(String args[]) {
		
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(ProjectConfig.class);
	     Person person = context.getBean(Person.class);
//	     String[] names = context.getBeanNamesForType(Vehicle.class);
	     
	     /*
	        Vehicle vehicle = context.getBean(Vehicle.class);
	        vehicle.getVehicleServices().setSpeakers(new BoseSpeakers());
	        vehicle.getVehicleServices().playMusic();
	        vehicle.getVehicleServices().setTyres(new MichelinTyres());
	        vehicle.getVehicleServices().moveVehicle();
	        
	        */
	     
	        person.getVehicle().getVehicleServices().playMusic();
	        person.getVehicle().getVehicleServices().moveVehicle();
	}
}
