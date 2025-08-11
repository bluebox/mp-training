package singleton.ex_14;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"singleton.ex_14"})
@ComponentScan(basePackageClasses = {singleton.ex_14.Vehicle.class,
			singleton.ex_14.Person.class,singleton.ex_14.VehicleServices.class})
	public class ProjectConfig {

	}


