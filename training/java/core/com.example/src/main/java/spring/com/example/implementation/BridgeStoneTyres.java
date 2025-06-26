package spring.com.example.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import spring.com.example.interfaces.Tyres;

@Component
@Primary
public class BridgeStoneTyres implements Tyres {
	String name="BridgeStone tyres";
	@Override
	public String rotate() {
		return " vehicle with bridgestone tyres";
	}
}
