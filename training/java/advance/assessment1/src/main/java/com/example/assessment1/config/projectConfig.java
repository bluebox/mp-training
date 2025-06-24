package com.example.assessment1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.assessment1.beans.BoseSpeakers;
import com.example.assessment1.beans.BridgeStoneTyres;
import com.example.assessment1.beans.MichelinTyres;
import com.example.assessment1.beans.SonySpeakers;
import com.example.assessment1.beans.Speakers;
import com.example.assessment1.beans.Tyres;
import com.example.assessment1.beans.VehicleService;

@Configuration
public class projectConfig {
	@Bean
	public Tyres bridgeTyres()
	{
		return new BridgeStoneTyres();
		
	}
	@Bean
	public Tyres michelinTyres()
	{
		return new MichelinTyres();
		
	}
	@Bean
	public Speakers boseSpeakers()
	{
		return new BoseSpeakers();
		
	}
	@Bean
	public Speakers sonySpeakers()
	{
		return new SonySpeakers();
		
	}
	
	@Bean
	public VehicleService boseBridgeService()
	{
		return new VehicleService(boseSpeakers(), bridgeTyres());
		
	}
	

}
