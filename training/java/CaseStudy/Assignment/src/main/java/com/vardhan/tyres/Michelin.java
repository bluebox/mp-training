package com.vardhan.tyres;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class Michelin implements Tyres {

	public void rotating() {
		System.out.println("The Car is wearing Michelin Rubbers");
	}
}
