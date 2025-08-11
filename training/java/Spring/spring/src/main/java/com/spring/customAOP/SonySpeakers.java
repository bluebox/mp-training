package com.spring.customAOP;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers {

	@Override
	public String makeSound(Song song) {
		System.out.println("inside sony speakers class");
		return "Playing the song " + song.getTitle() + " by " + song.getSingerName() + " with Sony speakers";
	}

}