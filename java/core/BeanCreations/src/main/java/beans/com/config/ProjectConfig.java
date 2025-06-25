package beans.com.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import beans.com.bean.Person;


@Configuration
public class ProjectConfig {
	
	@Bean
	Person person() {
		var per = new Person();
		per.setId(20);
		per.setName("Jai");
		return per;
	}
	
	@Bean
	String hello() {
		return "Spring Demo called!";
	}
}
