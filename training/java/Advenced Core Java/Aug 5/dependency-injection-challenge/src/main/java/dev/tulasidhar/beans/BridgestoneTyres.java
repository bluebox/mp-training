package dev.tulasidhar.beans;

import org.springframework.stereotype.Component;

@Component
public class BridgestoneTyres implements Tyres {

	@Override
	public void rotate() {
		System.out.println("BridgeStone tyres are rotating with great friction");

	}

}
