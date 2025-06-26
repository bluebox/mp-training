package com.spring.assignment;

import com.spring.assignment.model.Speakers;

public class BoseSpeaker implements Speakers {
	@Override
	public void playMusic() {
		System.out.println("Muic is Playing using BoseSpeakers ");
	}
}
