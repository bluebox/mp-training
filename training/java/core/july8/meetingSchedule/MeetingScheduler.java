package dev.tulasidhar.july8.meetingSchedule;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class MeetingScheduler {
    public static void main(String[] args) {
        ZoneId eastCoast = ZoneId.of("America/New_York");
        ZoneId sydney = ZoneId.of("Australia/Sydney");
       
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        for (int day = 1; day <= 10; day++) {
            LocalDate date = today.plusDays(day);
            //System.out.println("Current day:" +date);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                continue;
            }
            
            for (int hour = 7; hour <= 20; hour++) {
                LocalDateTime eastCoastTime = LocalDateTime.of(date, LocalTime.of(hour, 0));
                ZonedDateTime eastCoastZoned = eastCoastTime.atZone(eastCoast);
                System.out.println("east coast with atZone "+eastCoastZoned);
                ZonedDateTime sydneyZoned = eastCoastZoned.withZoneSameInstant(sydney);
                //System.out.println("Sydeney with withZoneSameInstant : "+sydneyZoned);

                LocalDateTime sydneyTime = sydneyZoned.toLocalDateTime();
                DayOfWeek sydneyDay = sydneyTime.getDayOfWeek();
                int sydneyHour = sydneyTime.getHour();
                
                if (sydneyDay != DayOfWeek.SATURDAY && sydneyDay != DayOfWeek.SUNDAY && 
                    sydneyHour >= 7 && sydneyHour <= 20) {
                    System.out.println("East Coast: " + eastCoastTime.format(formatter) + 
                                     " | Sydney: " + sydneyTime.format(formatter));
                }
            }
        }
    }
}