package com.spring.asp.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.asp.beans.Flight;
import com.spring.asp.config.Config;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Flight flight = context.getBean(Flight.class);
		
//		flight.isStarted();
////		flight.isTakingOff();
//		flight.isLanding();
////		flight.isStopped();
		
		try {
			flight.isStarted();
		}catch(NullPointerException e) {
			System.out.println("There is Somthing Wrong !!!");
		}
		flight.isLanding();
		
		flight.flightName();
	}

}
