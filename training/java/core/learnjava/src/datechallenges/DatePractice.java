package datechallenges;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;



public class DatePractice {
	public static void main(String args[] ){
		LocalDate date = LocalDate.now();
		
		LocalTime time = LocalTime.now();
		
		System.out.println("Date : "+date);
		
		System.out.println("year of date : "+date.getYear());
		
		System.out.println("day of a month : "+date.getDayOfMonth());
		
		System.out.println("Day of a week : "+date.getDayOfWeek());
		
		System.out.println("Day of year : "+date.getDayOfYear());
		
		System.out.println("Time : "+time);
		
		LocalDate custom_date = LocalDate.of(2002, 07, 19);
		
		System.out.println("Day of week of cutom date : "+custom_date.getDayOfWeek());
		
		LocalDate cus_date = LocalDate.parse("2002-05-19");
		
		System.out.println("Day of week of custom date : "+cus_date.getDayOfWeek());
		
		cus_date  = cus_date.plusMonths(2);
		
		System.out.println("custom date : "+cus_date);
		
		System.out.println("is after today date : "+cus_date.isAfter(date));
		
		System.out.println("custom Date is before today date : "+cus_date.isBefore(date));
		
		System.out.println("custom date is a leap year or not : "+cus_date.isLeapYear());
		
		System.out.println("hours : "+time.getHour());
		
		System.out.println("Minutes : "+time.getMinute());
		
		System.out.println("Seconds : "+time.getSecond());
		
		System.out.println("Nano Seconds : "+time.getNano());
		
		LocalTime cus_time = LocalTime.parse("03:00");
		
		System.out.println("cus_time : "+cus_time.getHour());
		
		System.out.println("cus_time is after time  : "+cus_time.isAfter(time));
		
		System.out.println("cus_time is before time : "+cus_time.isBefore(time));
		
		
	}
}
