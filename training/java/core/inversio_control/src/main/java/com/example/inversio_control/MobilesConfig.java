package com.example.inversio_control;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MobilesConfig {

	@Bean
	public Color getColor()
	{
		return new Color();
	}
	
	@Bean
	public Mobiles getOnePlusObject(Color getColor)
	{
		return new OnePlus(getColor);
	}
	
	@Bean
	public Mobiles getRedmiObject()
	{
		return new Redmi();
	}
	
	@Bean
	public Mobiles getRealmeObject()
	{
		return new Realme();
	}
}
