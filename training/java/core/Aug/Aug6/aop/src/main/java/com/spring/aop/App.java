package com.spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.ProjectConfig;
import com.spring.model.Song;
import com.spring.services.VehicleServices;

public class App {
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {

		var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
		var vehicleServices = context.getBean(VehicleServices.class);

		System.out.println(vehicleServices.getClass());
		Song song = new Song();
		song.setTitle("Blank Space");
		song.setSingerName("Taylor Swift");
		boolean vehicleStarted = true;

		String moveVehicleStatus = vehicleServices.moveVehicle(vehicleStarted);
		String playMusicStatus = vehicleServices.playMusic(vehicleStarted, song);
		String applyBrakeStatus = vehicleServices.applyBrake(vehicleStarted);

	}
}
