package com.spring.implementation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.spring.interfaces.Speakers;
import com.spring.model.Song;

@Component
@Primary
public class SonySpeakers implements Speakers {

	public String makeSound(Song song) {
		return "Playing the song " + song.getTitle() + " by " + song.getSingerName() + " with Sony speakers";
	}

}
