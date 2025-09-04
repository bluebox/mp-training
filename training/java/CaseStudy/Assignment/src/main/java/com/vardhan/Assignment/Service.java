package com.vardhan.Assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vardhan.speakers.Speaker;
import com.vardhan.tyres.Tyres;
@Component
public class Service {
	
	@Autowired
	Tyres tyres;
	
	@Autowired
	Speaker speaker;
	
	public void Sound() {
		speaker.noice();
	}
	
	public void Roll() {
		tyres.rotating();
	}

}
