package com.spring.example2;

import com.spring.example2.model.Music;

public class Sony implements Music{

	@Override
	public void play() {
		System.out.println("Music is playing using Sony Speakers");
		
	}

}
