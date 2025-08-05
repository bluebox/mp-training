package dev.kaushik.autowiring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import dev.kaushik.autowiring.impl.BoseSpeakers;
import dev.kaushik.autowiring.impl.MichelinTyres;
import dev.kaushik.autowiring.interfaces.Speakers;
import dev.kaushik.autowiring.interfaces.Tyres;
import dev.kaushik.autowiring.services.VehicleServices;

@Configuration
@ComponentScan(basePackageClasses = { dev.kaushik.autowiring.beans.Person.class,
		dev.kaushik.autowiring.beans.Vehicle.class }, basePackages = { "dev.kaushik.autowiring.implementation",
				"dev.kaushik.autowiring.services","dev.kaushik.autowiring.interfaces" })
public class ProjectConfig {
	@Bean 
    public Tyres tyres() {
        return new MichelinTyres(); 
    }
	@Bean
	public VehicleServices services() {
		return new VehicleServices();
	}
	@Bean 
	public Speakers speakers() {
		return new BoseSpeakers();
	}
}
