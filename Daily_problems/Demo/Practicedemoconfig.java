
package com.Springpractise.Demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;





@Configuration
@ComponentScan(basePackages = "com.Springpractise.Demo")
public class Practicedemoconfig {
	@Bean
public String demo() {
		// TODO Auto-generated method stub
System.out.println("this is demo");
return "Ravi kumar";
	}
	@Bean("Hanuman")
	public Person person() {
		return new Person("Hanuman",99995);
	}
	@Primary
	@Bean
	public Person person1() {
		return new Person("Hritik",45);
	}
	@Bean("shiva")
	public Person person3() {
		return new Person("Shiva",67);
	}
	
}
