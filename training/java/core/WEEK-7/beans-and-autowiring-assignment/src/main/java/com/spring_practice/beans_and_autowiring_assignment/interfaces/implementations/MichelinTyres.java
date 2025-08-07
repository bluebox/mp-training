package com.spring_practice.beans_and_autowiring_assignment.interfaces.implementations;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.spring_practice.beans_and_autowiring_assignment.interfaces.Tyres;

@Component
@Primary
public class MichelinTyres implements Tyres {

	@Override
	public String moveVehicle() {
		return "moving vehicle with Michelin Tyres";
	}

}
