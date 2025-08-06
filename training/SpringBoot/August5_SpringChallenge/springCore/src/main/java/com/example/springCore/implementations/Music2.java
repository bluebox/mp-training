package com.example.springCore.implementations;
import org.springframework.stereotype.Component;

import com.example.springCore.interfaces.Speakers;

@Component
public class Music2 implements Speakers {

	@Override
	public String makeSound() {
		return "Music2 is playing";
	}

}
