package com.karthik.assign.implementations;

import org.springframework.stereotype.Component;

import com.karthik.assign.interfaces.Tyres;

@Component
public class YTyres implements Tyres {

	@Override
	public String rotate() {
		return "Vechile uses Xtyres to move";
	}

}
