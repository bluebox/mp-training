package dev.kaushik.firstSpring;

import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "dev.kaushik.firstSpring")
public class SimConfig {

	@Bean(name = "jioSim", initMethod = "init", destroyMethod = "destroy")
	public Sim sim1() {
		return new Jio();
	}

	@Bean(name = "airtel", initMethod = "init", destroyMethod = "destroy")
	public Sim sim2() {
		return new Airtel();
	}

	@Primary
	@Bean(initMethod = "init", destroyMethod = "destroy")
	Sim getRandomSim() {
		Sim[] sims = new Sim[] { new Airtel(), new Jio(), new Idea(), new Bsnl() };
		return sims[new Random().nextInt(4)];
	}
}
