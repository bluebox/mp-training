package com.spring.BeansAndAutowiringTask;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers {

	@Override
	public void makeSound() {
		System.out.println("Playing songs on Sony speakers");
	}

}
