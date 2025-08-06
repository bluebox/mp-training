package com.example.springCore.implementations;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.springCore.interfaces.Speakers;


@Component
@Primary
public class Music1 implements Speakers {

	public String makeSound() {
		return "Music1 is playing";
	}
	


}
