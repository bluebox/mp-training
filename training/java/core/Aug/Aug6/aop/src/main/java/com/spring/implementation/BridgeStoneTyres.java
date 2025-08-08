package com.spring.implementation;

import org.springframework.stereotype.Component;

import com.spring.interfaces.Tyres;

@Component
public class BridgeStoneTyres implements Tyres {

	@Override
	public String rotate() {
		return "Vehicle moving with the help of BridgeStone tyres";
	}

	@Override
	public String stop() {
		return "Vehicle stopped with the help of BridgeStone tyres";
	}
}
