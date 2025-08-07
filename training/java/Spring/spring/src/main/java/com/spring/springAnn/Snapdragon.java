package com.spring.springAnn;

import org.springframework.stereotype.Component;

@Component
public class Snapdragon implements MobileProcessor {

	@Override
	public void process() {
		System.out.println("World's best CPU");
	}

}
