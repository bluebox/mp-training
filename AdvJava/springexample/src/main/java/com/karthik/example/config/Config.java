package com.karthik.example.config;


import com.karthik.example.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class Config {

    @Bean
    Vehicle vehicle() {
        var veh = new Vehicle();
        veh.setName("Audi 8");
        return veh;
    }

    @Bean
    Vehicle vehicle1() {
        var veh = new Vehicle();
        veh.setName("Honda");
        return veh;
    }
    
    @Bean
    Vehicle vehicle2() {
        var veh = new Vehicle();
        veh.setName("Nexa");
        return veh;
    }
    
    @Bean
    @Primary
    String hello() {
        return "Hello World";
    }

    @Bean
    Integer number() {
        return 16;
    }
    
    @Bean(name="Safa")
    Vehicle vehicle3() {
    	  var veh = new Vehicle();
          veh.setName("Safari");
          return veh;
    }
    
    @Bean(value="World")
    String world() {
        return "Hello World Hello World";
    }
    
    @Bean("HWorld")
    String helloworld() {
        return "Hello World Hello World Hello World ";
    }
}
