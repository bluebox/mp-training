package dev.kaushik.autowiring.impl;

import org.springframework.stereotype.Component;

import dev.kaushik.autowiring.interfaces.Tyres;

@Component
public class MichelinTyres implements Tyres {
	
	@Override
	public void rotate() {
		System.out.println("michelin tyres rotating");
	}

}
