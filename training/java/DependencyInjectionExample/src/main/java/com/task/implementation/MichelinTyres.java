package com.task.implementation;

import org.springframework.stereotype.Component;

import com.task.interfaces.Tyres;

@Component(value="MichelinTyres")
public class MichelinTyres implements Tyres{
	public String rotate() {
		return "michelin tyres rotating";
	}
}
