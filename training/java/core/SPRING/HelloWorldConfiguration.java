package com.maven.Rohan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


record Person(String name,int age, Address adress) { };
record Address (String firstLIne, String city) {};
@Configuration
public class HelloWorldConfiguration {
	
	
    @Bean
	public String name() {
		return "ROHAN"; 
	}
    
    @Bean
   	public int  age() {
   		return 25; 
   	}
    
    @Bean 
    public Person person() {
    	return new Person ("Ravi",25,new Address("madhpur","hyderbad") );
    	
    }
    
    @Bean 
    public Person person2() {
    	return new Person (name(),age(),address());
    	
    }
    @Bean(name = "santosh")
    public Address address() {
    	return new Address("plot no19","vanastalipuram");
    }
    
}

