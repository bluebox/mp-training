package com.example.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.interfaces.Tyres;

@Component
@Primary
public class TVSTyres implements Tyres {

	@Override
	public String rotate() {
		return "TVS Tyres are rotating...";
	}

}
