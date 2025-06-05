package myRandamization;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class DateTime {
	public static void main(String[] args) {
		LocalDate local = LocalDate.now();
		System.out.println("local date:" + local);
		LocalTime localTime = LocalTime.now();
		System.out.println("local time:" + localTime);
		LocalDateTime localDateTime1 = LocalDateTime.now();
		System.out.println("local date and time:" + localDateTime1);
//		LocalDate local = LocalDate.now();
		LocalDate local2 = local.minusDays(100);
		System.out.println("local date  atter 20 days:" + local.plusDays(20));
		System.out.println("period between date1 and date2 is:"+ Period.between(local, local2).getMonths()+" months and "+Period.between(local, local2).getDays()+" days");
		Instant currentInstant= Instant.now();
		System.out.println(currentInstant);
		System.out.println("Epoch seconds "+currentInstant.getEpochSecond());
//		System.out.println(currentInstant)
		
	}
}
