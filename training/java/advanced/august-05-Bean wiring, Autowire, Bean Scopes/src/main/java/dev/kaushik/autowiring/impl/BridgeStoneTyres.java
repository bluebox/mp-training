package dev.kaushik.autowiring.impl;

import org.springframework.stereotype.Component;

import dev.kaushik.autowiring.interfaces.Tyres;

@Component
public class BridgeStoneTyres implements Tyres {

	@Override
	public void rotate() {
		System.out.println("bridgestone tyres rotating");
	}

}
