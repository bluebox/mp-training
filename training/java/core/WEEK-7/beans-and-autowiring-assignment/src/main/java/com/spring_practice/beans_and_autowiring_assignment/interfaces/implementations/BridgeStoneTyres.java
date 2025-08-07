package com.spring_practice.beans_and_autowiring_assignment.interfaces.implementations;

import org.springframework.stereotype.Component;

import com.spring_practice.beans_and_autowiring_assignment.interfaces.Tyres;

@Component
public class BridgeStoneTyres implements Tyres {

	@Override
	public String moveVehicle() {
		return "moving vehicle with Bridge Stone Tyres";
	}

}
