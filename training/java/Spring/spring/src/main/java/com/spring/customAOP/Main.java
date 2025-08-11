package com.spring.customAOP;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		VehicleService vehicleServices = context.getBean(VehicleService.class);
		System.out.println(vehicleServices.getClass());
		Song song = new Song();
		song.setTitle("Blank Space");
		song.setSingerName("Taylor Swift");
		boolean vehicleStarted = true;
		String moveVehicleStatus = vehicleServices.moveVehicle(vehicleStarted);
		System.out.println("---------------------------");
		String playMusicStatus = vehicleServices.playMusic(vehicleStarted, song);
		System.out.println("---------------------------");
		String applyBrakeStatus = vehicleServices.applyBrake(vehicleStarted);
	}
}
