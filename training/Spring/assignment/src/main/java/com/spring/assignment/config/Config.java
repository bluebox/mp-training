package com.spring.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring.assignment.BoseSpeaker;
import com.spring.assignment.SonySpeakers;
import com.spring.assignment.model.Speakers;

@Configuration
@ComponentScan(basePackages="com.spring.assignment")
public class Config {
	@Bean
	public Speakers speaker1() {
		Speakers spk1 = new BoseSpeaker();
		return spk1;
	}
	
	@Bean 
	public Speakers speaker2() {
		Speakers spk2 = new SonySpeakers();
		return spk2;
	}
	
}
