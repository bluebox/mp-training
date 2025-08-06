package com.example.assignment.Assignment;

import org.springframework.stereotype.Component;

@Component("bridgetyres")
public class BridgeStoneTyres implements Tyres{

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
		System.out.println("Bridgestone tyres are very good");
		
	}

}
