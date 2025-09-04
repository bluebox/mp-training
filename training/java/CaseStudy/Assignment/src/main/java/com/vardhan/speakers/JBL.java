package com.vardhan.speakers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class JBL implements Speaker{

	public void noice() {
		System.out.println("The car is using JBL speakers");
	}

}
