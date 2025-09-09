package com.vardhan.speakers;

import org.springframework.stereotype.Component;

@Component
public class Bose implements Speaker {

	public void noice() {
		System.out.println("The car is using Bose Speakers");
	}

}
