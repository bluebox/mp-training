package javaCoreFundamentalsRegEx;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;


public class MeetingScheduler {
    public static void main(String[] args) {
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZoneId sydneyZone = ZoneId.of("Australia/Sydney");
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.plusDays(1);
        LocalDate endDate = startDate.plusDays(10);

        DateTimeFormatter nyFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, h:mm a", Locale.US);
        DateTimeFormatter sydneyFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy, h:mm a", Locale.ENGLISH);

        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) continue;

            for (int hour = 7; hour <= 20; hour++) {
                ZonedDateTime nyTime = ZonedDateTime.of(date, LocalTime.of(hour, 0), newYorkZone);
                ZonedDateTime sydneyTime = nyTime.withZoneSameInstant(sydneyZone);

                ZonedDateTime startValidTime = sydneyTime.withHour(7).withMinute(0).withSecond(0).withNano(0);
                ZonedDateTime endValidTime = sydneyTime.withHour(20).withMinute(0).withSecond(0).withNano(0);

                if (ChronoUnit.HOURS.between(startValidTime, sydneyTime) >= 0 &&
                    ChronoUnit.HOURS.between(sydneyTime, endValidTime) >= 0) {
                    System.out.println("Jane [America/New_York] : " + nyTime.format(nyFormatter) + 
                                      " <--- Joe [Australia/Sydney] : " + sydneyTime.format(sydneyFormatter));
                }
            }
        }
    }
}
