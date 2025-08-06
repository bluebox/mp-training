package com.example.assignment.Assignment;

import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres{

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
		System.out.println("the michelin tyres are smooth");
	}

}
