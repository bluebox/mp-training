package Spring.DemoProject;


	


	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;

	@Configuration
	public class projectconfig {

	    @Bean
	    Vechiclee vehicle() {
	        var veh = new Vechiclee();
	        veh.setName("Audi 8");
	        return veh;
	    }

	 
	    @Bean
	    String hello() {
	        return "Hello World";
	    }

	    @Bean
	    Integer number() {
	        return 16;
	    }
	}


