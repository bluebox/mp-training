package annotations.project;
import annotations.project.Person;
import annotations.project.pvehicle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class pvprojectconfig {
	@Bean
	public pvehicle vehicle() {
		pvehicle vehicle = new pvehicle();
		vehicle.setName("Toyota");
		return vehicle;
				
		
	}
	@Bean 
	public Person person(pvehicle vehicle) {
		Person person = new Person();
		person.setName("kavitha");
		person.setVehicle(vehicle);
		
		
		return person;
		
	}
	

}
