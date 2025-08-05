package com.saketh.Advbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class Car {
	@Autowired
	Tyre tyre;
	String name;
	Engine engine;
	Music music;
	
	@Autowired
	public Car(Engine engine) {
		engine.setName("Diker");
		engine.start();
		this.engine = engine;
	}

	public Tyre getTyre() {
		return tyre;
	}
	
	public void setTyre(Tyre tyre) {
		this.tyre = tyre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Music getMusic() {
		return music;
	}
	@Autowired
	public void setMusic(Music music) {
		music.setName("Abibas");
		this.music = music;
	}
}
