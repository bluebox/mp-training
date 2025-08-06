package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AnimalDesc {

	private Animals animal;
	
	@Autowired
	@Qualifier(value="Bird")
	public void setAnimal(Animals animal) {
		this.animal = animal;
	}

	public Animals getAnimal() {
		return animal;
	}

}
