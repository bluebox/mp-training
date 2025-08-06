package com.maven.Implementation;

import org.springframework.stereotype.Component;

import com.maven.Interface.Tyres;



@Component("Michellin")
public class MichellinTyres implements Tyres {

	@Override
	public void rotate() {
		System.out.println("Using Michellin Tyres for the Vehicle");
		
	}

}
