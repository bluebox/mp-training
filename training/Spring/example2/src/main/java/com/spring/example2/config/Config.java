package com.spring.example2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.spring.example2.BrideStone;
import com.spring.example2.Jbl;
import com.spring.example2.MRFTyres;
import com.spring.example2.Sony;
import com.spring.example2.Vehicle;
import com.spring.example2.model.Music;
import com.spring.example2.model.Tyre;

@Configuration
public class Config {

	@Bean
	@Primary
	public Music player2() {
		Music ply2 = new Jbl();
		return ply2;
	}
	
	@Bean
	public Tyre move1() {
		Tyre mov1 = new MRFTyres();
		return mov1;
	}
	
	@Bean
	@Primary
	public Tyre move2() {
		Tyre mov2 = new BrideStone();
		return mov2;
	}
	
	@Bean
	public Vehicle car(Music player1) {
		Vehicle car = new Vehicle();
		car.setTyre(move1());
		car.setMusic(player1);
		return car;
	}
	
	@Bean
	public Music player1() {
		Music ply1 = new Sony();
		return ply1;
	}
	
	
}
