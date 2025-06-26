package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

import com.example.dao.Speaker;
import com.example.dao.Tyres;
import com.example.dao.Vehicle;

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
	@Bean
	public Vehicle vehicle(@Qualifier("BoseSpeakersBean") Speaker s) {
		Vehicle v=new Vehicle();
		v.speaker().setName(s.getName());
		return v;
	}
}
