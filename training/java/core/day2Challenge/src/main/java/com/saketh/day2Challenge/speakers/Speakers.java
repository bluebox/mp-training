package com.saketh.day2Challenge.speakers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public interface Speakers {
	public void makeSound();
}
