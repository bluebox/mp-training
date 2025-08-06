package com.springexamples.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.springexamples.interfaces.Tyres;

@Component
@Primary
public class BridgeStoneTyres implements Tyres{

	@Override
	public void rotate() {
		System.out.println("BridgeStone tyres are rolling");
	}

}
