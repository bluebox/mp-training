package com.example.assessment1.beans;

import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTyres implements Tyres{
	@Override
	public void rotate() {
		System.out.println(" BridgeStone tyres are rotating");
		
	}

}
