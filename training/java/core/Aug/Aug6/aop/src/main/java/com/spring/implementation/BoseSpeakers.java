package com.spring.implementation;

import org.springframework.stereotype.Component;

import com.spring.interfaces.Speakers;
import com.spring.model.Song;

@Component
public class BoseSpeakers implements Speakers {

	public String makeSound(Song song) {
		return "Playing the song " + song.getTitle() + " by " + song.getSingerName() + " with Bose speakers";
	}

}
