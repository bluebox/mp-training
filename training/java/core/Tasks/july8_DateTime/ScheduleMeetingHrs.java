package corejava.july8_DateTime;
import java.time.*;
import java.time.format.DateTimeFormatter;
public class ScheduleMeetingHrs {

	public static void main(String[] args) {
		ZoneId zoneNY = ZoneId.of("America/New_York");
		ZoneId zoneSydney = ZoneId.of("Australia/Sydney");
		
		LocalDate today = LocalDate.now();
		LocalDate startDate = today.plusDays(1); 
		LocalDate endDate = today.plusDays(10); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			DayOfWeek day = date.getDayOfWeek();
			if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
				continue;
		    }
			for (int hour = 7; hour < 21; hour++) {
				LocalTime time = LocalTime.of(hour, 0);
		        ZonedDateTime newyorkTime = ZonedDateTime.of(date, time, zoneNY);
		        ZonedDateTime sydneyTime = newyorkTime.withZoneSameInstant(zoneSydney);

                int sydneyHour = sydneyTime.getHour();
		        DayOfWeek sydneyDay = sydneyTime.getDayOfWeek();

		        if (sydneyDay != DayOfWeek.SATURDAY && sydneyDay != DayOfWeek.SUNDAY && sydneyHour >= 7 && sydneyHour < 21) {
		        	System.out.println("Meeting time: "
		                        + newyorkTime.format(formatter) + " (New York) | "
		                        + sydneyTime.format(formatter) + " (Sydney)");
		        }
		    }
		}

	}

}
