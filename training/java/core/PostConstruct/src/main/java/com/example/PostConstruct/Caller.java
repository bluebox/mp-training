package com.example.PostConstruct;



import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class Caller {

	public Caller() {
		super();
		System.out.println("call is comming");
	}
	
	public void liftcall()
	{
		System.out.println("lift the call");
	}
	@PostConstruct
	public void cutcall()
	{
		System.out.println("cut the call");
	}

}
