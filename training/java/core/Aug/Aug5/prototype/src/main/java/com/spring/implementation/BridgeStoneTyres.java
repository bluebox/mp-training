package com.spring.implementation;

import org.springframework.stereotype.Component;

import com.spring.interfaces.Tyres;

@Component
public class BridgeStoneTyres implements Tyres {

	public String rotate() {
		return "Vehicle moving with BridgeStone tyres";
	}
}
