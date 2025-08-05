package com.saketh.day2Challenge.tyres;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MichelinTyres implements Tyres{
	@Override
	public void rotate() {
		System.out.println("MichelinTyres are rotating");
	}
}
