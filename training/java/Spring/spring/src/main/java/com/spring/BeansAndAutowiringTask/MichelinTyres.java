package com.spring.BeansAndAutowiringTask;

import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres {

	@Override
	public void rotate() {
		System.out.println("Michelin tyres are rotating");
	}

}
