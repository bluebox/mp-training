package annotations.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 

@Configuration
public class projectconfig {
	@Bean
	Vehicle vehicle() {
		Vehicle veh = new Vehicle();
		veh.setName("audi");
		return veh;
		
	}
	@Bean
	String hello() {
		return "hello world";
		
	}
	
	
	

}
