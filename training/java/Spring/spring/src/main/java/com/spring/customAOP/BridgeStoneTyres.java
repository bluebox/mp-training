package com.spring.customAOP;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
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
