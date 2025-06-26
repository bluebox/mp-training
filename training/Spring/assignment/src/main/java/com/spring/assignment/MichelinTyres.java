package com.spring.assignment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.spring.assignment.model.Tyres;

@Component
@Primary
public class MichelinTyres implements Tyres {
	@Override
	public void move() {
		System.out.println("Cars is moving using MichelinTyres");
	}
}
