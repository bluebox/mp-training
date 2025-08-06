package com.example.assignment.Assignment;

import org.springframework.stereotype.Component;

@Component("bosespeakers")
public class BoseSpeakers implements Speakers {

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("Bose speakers are  making sounds");
		
	}

}
