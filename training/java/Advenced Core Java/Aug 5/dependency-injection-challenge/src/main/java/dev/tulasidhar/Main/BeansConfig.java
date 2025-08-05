package dev.tulasidhar.Main;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import dev.tulasidhar.beans.ApolloTyres;
import dev.tulasidhar.beans.BoatSpeaker;
import dev.tulasidhar.beans.BoseSpeaker;
import dev.tulasidhar.beans.BridgestoneTyres;
import dev.tulasidhar.beans.Person;
import dev.tulasidhar.beans.Speaker;
import dev.tulasidhar.beans.Tyres;
import dev.tulasidhar.beans.Vehicle;
import dev.tulasidhar.beans.VehicleService;

@Configuration
public class BeansConfig {
	
	@Bean
	Person person() {
		return new Person();
	}
	
	@Bean
	Vehicle vehicle(){
		return new Vehicle();
	}
	
	@Bean
	VehicleService vehService() {
		return new VehicleService();
	}
	
	
	
	
	@Bean
	@Primary
	Tyres bridgeStonetyres() {
		return new BridgestoneTyres();
	}
	
	@Bean
	@Lazy
	Tyres apollotyres() {
		System.out.println("apollotyres bean is created");
		return new ApolloTyres();
	}
	
	
	
	@Bean
	@Primary
	Speaker boseSpeaker() {
		return new BoseSpeaker();
	}
	
	@Bean
	Speaker boatSpeaker() {
		return new BoatSpeaker();
	}
	
	
	
}

