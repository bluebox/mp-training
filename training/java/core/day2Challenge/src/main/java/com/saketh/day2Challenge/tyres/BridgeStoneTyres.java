package com.saketh.day2Challenge.tyres;

import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTyres implements Tyres{
	@Override
	public void rotate() {
		System.out.println("Bridegstone tyres are rotating");
	}
}
