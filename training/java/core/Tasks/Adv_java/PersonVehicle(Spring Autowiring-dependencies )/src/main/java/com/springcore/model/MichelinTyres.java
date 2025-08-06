package com.springcore.model;

import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres{

	@Override
	public void rotate() {
		System.out.println("Moving with MichelinTyres");
	}

}
