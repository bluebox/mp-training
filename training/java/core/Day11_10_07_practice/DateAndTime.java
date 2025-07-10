package Day11_10_07_practice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateAndTime {
	public static void main(String[] args) {
		LocalDate date=LocalDate.now();
		System.out.println(date);
		System.out.println(date.getDayOfMonth());
		System.out.println(date.getDayOfYear());
		System.out.println(date.getMonthValue());
		System.out.println(date.getDayOfWeek());
		System.out.println(date.getYear());
		System.out.println(date.isLeapYear());
		
		System.out.println("_".repeat(70));
		
		LocalTime time= LocalTime.now();
		System.out.println(time);
		System.out.println(time.getHour());
		System.out.println(time.getMinute());
		System.out.println(time.getNano());

		System.out.println("_".repeat(70));

		LocalDateTime dt=LocalDateTime.now();
		System.out.println(dt);
		System.out.println(dt.getDayOfMonth());
		System.out.println(dt.getDayOfYear());
		System.out.println(dt.getHour());
		System.out.println(dt.getMinute());
		System.out.println(dt.getMonthValue());
		System.out.println(dt.getSecond());

	}
}
