package com.saketh.day2Challenge.tyres;

import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTyres implements Tyres{
	public void rotate() {
		System.out.println("Bridegstone tyres are rotating");
	}
}
