package com.springcore.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BridgeStoneTyres implements Tyres{

	@Override
	public void rotate() {
		System.out.println("Moving with BidgeStoneTyres");
	}
	
}
