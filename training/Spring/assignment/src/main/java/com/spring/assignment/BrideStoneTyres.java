package com.spring.assignment;

import org.springframework.stereotype.Component;

import com.spring.assignment.model.Tyres;

@Component
public class BrideStoneTyres implements Tyres {

	@Override
	public void move() {
		System.out.println("Vechile is moving using BrideStoneTyres");

	}

}
