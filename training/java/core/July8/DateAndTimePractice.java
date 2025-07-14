package July8;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

public class DateAndTimePractice {

	public static void main(String[] args) {
		
		System.out.println(LocalDate.now());
		System.out.println(LocalDate.of(2027, 7, 17));
		System.out.println(LocalDate.of(2026, Month.JULY, 17));
		System.out.println(LocalDate.ofYearDay(2026, 198));
		
		System.out.println("-----------");
		LocalDate d = LocalDate.parse("2003-07-17");
		System.out.println(d);		
		System.out.println(d.getYear());
		System.out.println(d.getMonth());
		System.out.println(d.getMonthValue());
		System.out.println(d.getDayOfMonth());
		System.out.println(d.getDayOfWeek());
		System.out.println(d.getDayOfYear());
		System.out.println(d.get(ChronoField.YEAR));
		System.out.println(d.get(ChronoField.MONTH_OF_YEAR));
		System.out.println(d.get(ChronoField.DAY_OF_MONTH));
		System.out.println(d.get(ChronoField.DAY_OF_YEAR));
		
	}
}
