package com.maven.Implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.maven.Interface.Tyres;

@Component
@Primary
public class BridgeStoneTyres implements Tyres {

	@Override
	public void rotate() {
	    System.out.println("Using BridgeStone Tyres for the Vehicle") ;
		
	}

}
