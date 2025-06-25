package com.example.assessment1.beans;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MichelinTyres implements Tyres {

	@Override
	public void rotate() {
		System.out.println("Michelin tyres are rotating");
		
	}

}
