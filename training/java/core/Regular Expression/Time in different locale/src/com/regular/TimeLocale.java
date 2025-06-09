package com.regular;

import java.time.ZonedDateTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class TimeLocale {
    public static void main(String[] args) {
        Locale locale = new Locale("en", "us");
       for(int i=1;i<6;i++) {
    	   for(int j=19;j<21;j++) {
    	   ZonedDateTime dateTime = ZonedDateTime.now()
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.of(i)))
            .withHour(j).withMinute(0).withSecond(0).withNano(0);
        // Formatter to display day and time in desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE h a", locale);

        // Print formatted output
        System.out.println(dateTime.format(formatter));
    	   }
       }
    }
}
