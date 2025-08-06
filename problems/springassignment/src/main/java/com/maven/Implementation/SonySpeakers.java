package com.maven.Implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.maven.Interface.Sounds;

@Component
@Primary
public class SonySpeakers implements Sounds{


	@Override
	public void makeSound() {
		System.out.println("Making sound with Sony speakers");	
	}
    
}
