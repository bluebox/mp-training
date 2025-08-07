package dev.tulasidhar.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {
	Heart heart;
	
	public Heart getHeart() {
		return heart;
	}
	
	
	public void setHeart(Heart heart) {
		this.heart = heart;
	}

	@Autowired
	public Person(Heart heart) {
		this.heart = heart;
	}
	
	
}
