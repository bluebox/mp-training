package differentLocalMeeting;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

 

public class LocalMain {
	public static void main(String[] args) {
		
		Employee jane = new Employee("Jane", "America/New_York");
		Employee joe = new Employee("Joe", "Australia/Sydney");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy, h:mm a");
		
		LocalDate today = LocalDate.now(jane.getZone());
		int daysChecked =0;
		
		while(daysChecked < 10) {
			
			today = today.plusDays(1);
			
			if(today.getDayOfWeek() == DayOfWeek.SATURDAY || today.getDayOfWeek() == DayOfWeek.SUNDAY ) {
				continue;
			}
			
			for(int i=7;i<=20;i++) {
				
				ZonedDateTime janeZone = jane.getZoneDateTime(today, i);
				ZonedDateTime joeZone = janeZone.withZoneSameInstant(joe.getZone());
				
				if(janeZone.getHour() >= 7 && janeZone.getHour() <= 20 && joeZone.getHour()>= 7 && joeZone.getHour() <= 20) {
					if(janeZone.getDayOfWeek() != DayOfWeek.SATURDAY && janeZone.getDayOfWeek() != DayOfWeek.SUNDAY &&
							joeZone.getDayOfWeek() != DayOfWeek.SATURDAY && joeZone.getDayOfWeek() != DayOfWeek.SUNDAY) {
						
						System.out.println(jane + " "+janeZone.format(formatter) + " <-----> "+ joe + " "+ joeZone.format(formatter));
					}
				}
			}
			
			daysChecked++;
		}
		
	}
}
