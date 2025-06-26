package com.spring.example2;

import com.spring.example2.model.Music;
import com.spring.example2.model.Tyre;

public class Vehicle {

	private Tyre tyre;
	private Music music;

	public Tyre getTyre() {
		return tyre;
	}

	public Music getMusic() {
		return music;
	}

	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

}
