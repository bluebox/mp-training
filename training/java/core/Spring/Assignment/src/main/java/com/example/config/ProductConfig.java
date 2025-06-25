package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import com.example.dao.Speaker;
import com.example.dao.Tyres;

@ComponentScan("com.example.dao")
public class ProductConfig {
	@Bean
	public Speaker sony(){
		Speaker s=new Speaker();
		s.setName("Sony Speakers");
		return s;
	}
	@Bean
	@Primary
	public Speaker BoseSpeakersBean() {
		Speaker s=new Speaker();
		s.setName("Bose Speakers");
		return s;
	}
	@Bean
	@Primary
	public Tyres bridgeStoneTyres() {
		Tyres t=new Tyres();
		t.setName("BridgeStone tyres");
		return t;
	}
	@Bean
	public Tyres michelinTyres() {
		Tyres t=new Tyres();
		t.setName("Michelin");
		return t;
	}
}
