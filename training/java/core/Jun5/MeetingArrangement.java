package Jun5;

import java.util.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class MeetingArrangement {
	
	public static void main(String[] args) {
		ZoneId nycZone=ZoneId.of("America/New_York");
		ZoneId sydZone=ZoneId.of("Australia/Sydney");
		
		LocalDate start=LocalDate.now().plusDays(1);
		LocalDate end=start.plusDays(10);
		
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("EEEE,MMMM d,yyyy,h:mm a",Locale.ENGLISH);
		
		for(LocalDate date=start;date.isBefore(end);date=date.plusDays(1)) {
			DayOfWeek day=date.getDayOfWeek();
			if(day==DayOfWeek.SATURDAY || day==DayOfWeek.SUNDAY) {
				continue;
			}
			for(int hour=7;hour<=20;hour++) {
				ZonedDateTime nyc=ZonedDateTime.of(date,LocalTime.of(hour, 0),nycZone);
				ZonedDateTime syd=nyc.withZoneSameInstant(sydZone);
				int sydHour=syd.getHour();
				if(sydHour>=7 && sydHour<=20) {
					System.out.println("Alice[NYC]: "+nyc.format(formatter)+"---Bob[SYD]: "+syd.format(formatter));
				}
			}
		}
	}
	
	
}
