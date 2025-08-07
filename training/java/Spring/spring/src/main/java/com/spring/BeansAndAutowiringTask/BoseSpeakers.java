package com.spring.BeansAndAutowiringTask;

import org.springframework.stereotype.Component;

@Component
public class BoseSpeakers implements Speakers {

	@Override
	public void makeSound() {
		System.out.println("Playing songs on Bose speakers");
	}

}
