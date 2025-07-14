package July8.DateTimeAndLocalizationChallenge;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
		ZoneId eastCoast = ZoneId.of("America/New_York");
		ZoneId sydney = ZoneId.of("Australia/Sydney");

		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		for (int day = 1; day <= 10; day++) {
			LocalDate date = today.plusDays(day);
			DayOfWeek dayOfWeek = date.getDayOfWeek();

			if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) continue;

			for (int hour = 7; hour <= 20; hour++) {
				LocalDateTime eastCoastTime = LocalDateTime.of(date, LocalTime.of(hour, 0));
				ZonedDateTime eastCoastZoned = eastCoastTime.atZone(eastCoast);
				ZonedDateTime sydneyZoned = eastCoastZoned.withZoneSameInstant(sydney);

				LocalDateTime sydneyTime = sydneyZoned.toLocalDateTime();
				DayOfWeek sydneyDay = sydneyTime.getDayOfWeek();
				int sydneyHour = sydneyTime.getHour();

				if (sydneyDay != DayOfWeek.SATURDAY && sydneyDay != DayOfWeek.SUNDAY && sydneyHour >= 7 && sydneyHour <= 20) {
					System.out.println("East Coast: " + eastCoastTime.format(formatter) + " | Sydney: " + sydneyTime.format(formatter));
				}
			}
		}
	}
}
