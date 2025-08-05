package com.karthik.assign.implementations;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.karthik.assign.interfaces.Tyres;

@Component
@Primary
public class XTyres implements Tyres{

	@Override
	public String rotate() {
		return "Vechile uses Xtyres to move";
	}
	

}
