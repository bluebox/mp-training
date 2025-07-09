package com.prana;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.*;

public class Zone1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of zones :" + ZoneId.getAvailableZoneIds().size());
        
        ZoneId.getAvailableZoneIds().stream()
              .filter(s -> s.startsWith("India"))
              .sorted()
              
              .forEach(System.out::println);
        
        LocalDateTime localDate = LocalDateTime.now();
        System.out.println(localDate);
        
        LocalDateTime myDateObj = LocalDateTime.now();
        System.out.println("Before formatting: " + myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("After formatting: " + formattedDate);
	}
	

}
