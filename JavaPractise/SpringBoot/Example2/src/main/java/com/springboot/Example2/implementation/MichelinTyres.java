package com.springboot.Example2.implementation;

import org.springframework.stereotype.Component;

import com.springboot.Example2.interfaces.Tyres;

@Component
public class MichelinTyres implements Tyres{

	@Override
	public String rotate() {
		// TODO Auto-generated method stub
		return "Moving with Michelin Tyres.";
	}

}
