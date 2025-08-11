package com.spring.customAOP;

import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres {

	@Override
	public String rotate() {
		return "Vehicle moving with the help of Michelin tyres";
	}

	@Override
	public String stop() {
		return "Vehicle stopped with the help of Michelin tyres";
	}

}
