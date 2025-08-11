package annotations.project;
import annotations.project.vehex3;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class projectconfigex3 {
	
	
	
	@Bean(name ="zozo")
	vehex3 vehicle1() {
		var veh = new vehex3();
		veh.setName("ZOZOO");
		
		return veh ;
		
	}
	@Bean(value ="zozocarr")
	vehex3 vehicle2() {
		var veh = new vehex3();
		veh.setName("ZOZOOcarr");
		
		return veh ;
		
	}
	@Bean("zozoauto")
	vehex3 vehicle3() {
		var veh = new vehex3();
		veh.setName("ZOZOOautoo");
		
		return veh ;
		
	}
	
	
	

}
