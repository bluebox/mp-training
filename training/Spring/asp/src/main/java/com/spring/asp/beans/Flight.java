package com.spring.asp.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.spring.asp.interfaces.CustomAnnotation;

@Component
public class Flight {
	
	private Flight flight;
	@Autowired
	public Flight(@Lazy Flight flight) {
		this.flight=flight;
	}

	public void isStarted() {
		System.out.println("Flight is Started");
		//isTakingOff()
		flight.isTakingOff();
		throw (new NullPointerException());
//		flight.isFling();
	}

	public void isTakingOff() {
		System.out.println("Flight is Taking Off");
	}
	
	public static void isFling() {
		System.out.println("Flight is flying ");
	}
	@CustomAnnotation
	public void isLanding() {
		System.out.println("Flight is Landing");
//		this.isStopped();
		
		flight.isStopped();
	}

//	public void isStopped() {
//		System.out.println("Flight is Stopped");
//	}

	private void isStopped() {
		System.out.println("Flight is Stopped");
	}
	
	public String flightName() {
		return "Emirates";
	}

}
