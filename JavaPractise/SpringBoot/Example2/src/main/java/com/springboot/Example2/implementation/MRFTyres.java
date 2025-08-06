package com.springboot.Example2.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.springboot.Example2.interfaces.Tyres;

@Component("mRFTyres")
@Primary
public class MRFTyres implements Tyres{

	@Override
	public String rotate() {
		// TODO Auto-generated method stub
		return "Moving with MRF Tyres .";
	}

}
