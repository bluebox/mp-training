package com.springexamples.beans;

import org.springframework.stereotype.Component;

import com.springexamples.interfaces.Tyres;

@Component
public class MichelinTyres implements Tyres{

	@Override
	public void rotate() {
		
		System.out.println("Michelin tyres are rolling");
	}

}
