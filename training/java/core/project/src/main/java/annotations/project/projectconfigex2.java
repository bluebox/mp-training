package annotations.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class projectconfigex2 {
	@Bean
	Vehicle vehicle1() {
		var veh = new Vehicle();
		veh.setName("carr");
		return veh;
		
	}
	
	@Bean
	Vehicle vehicle2() {
		var veh = new Vehicle();
		veh.setName("bike");
		return veh;

}

	@Bean
	Vehicle vehicle() {
		var veh = new Vehicle();
		veh.setName("airoplan");
		return veh;
	}
}