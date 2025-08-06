package com.example.springCore.implementations;

import org.springframework.stereotype.Component;

import com.example.springCore.interfaces.Tyres;


@Component
public class Tyre2 implements Tyres{
	
	public String rotate() {
		return "Tyre2 is rotating";
	}

}
