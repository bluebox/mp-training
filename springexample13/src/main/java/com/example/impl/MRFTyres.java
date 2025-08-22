package com.example.impl;

import org.springframework.stereotype.Component;

import com.example.interfaces.Tyres;


@Component
public class MRFTyres implements Tyres {

	@Override
	public String rotate() {
		return "MRF tyres rotating";
	}

}
