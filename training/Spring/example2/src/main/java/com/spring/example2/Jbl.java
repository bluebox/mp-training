package com.spring.example2;

import com.spring.example2.model.Music;

public class Jbl implements Music{

	@Override
	public void play() {
		System.out.println("Music is playing using Jbl Speakers");
	}

}
