package com.spring.assignment;

import com.spring.assignment.model.Speakers;

public class SonySpeakers implements Speakers {
	@Override
	public void playMusic() {
		System.out.println("Music is playing using SonySpeakers");
	}
}
