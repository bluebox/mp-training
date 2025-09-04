package com.vardhan.tyres;

import org.springframework.stereotype.Component;

@Component
public class MRF implements Tyres{

	public void rotating() {
		System.out.println("The Car is wearing MRF Tyres");
	}

}
