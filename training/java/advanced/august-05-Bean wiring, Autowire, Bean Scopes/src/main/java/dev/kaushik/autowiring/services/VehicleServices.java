package dev.kaushik.autowiring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.kaushik.autowiring.interfaces.Speakers;
import dev.kaushik.autowiring.interfaces.Tyres;

@Component
public class VehicleServices {
	@Autowired
	private Tyres tyres;

	@Autowired
	private Speakers speakers;

	public Tyres getTyres() {
		return tyres;
	}

	public void setTyres(Tyres tyres) {
		this.tyres = tyres;
	}

	public Speakers getSpeakers() {
		return speakers;
	}

	public void setSpeakers(Speakers speakers) {
		this.speakers = speakers;
	}

}
