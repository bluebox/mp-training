package TimeAndDate;
import java.time.*;

public class TimeAndDate {
	public static void main(String[] args) {
		LocalDate date= LocalDate.now();
		LocalTime time= LocalTime.now();
		LocalDateTime dt=LocalDateTime.now();
		LocalDate a= LocalDate.of(2026,7,23);
		LocalDate b= LocalDate.of(2025,2,24);

		System.out.println("Date : "+date);
		System.out.println("Time : "+time);
		System.out.println("Date and Time : "+dt);
		System.out.println("Day is : "+date.getDayOfMonth());
		System.out.println("Day of week : "+date.getDayOfWeek());
		System.out.println("Day of year : "+date.getDayOfYear());
		System.out.println("Month : "+date.getDayOfMonth());
		System.out.println("Month value : "+date.getMonthValue());
		System.out.println("is Leap year : "+date.isLeapYear());
		System.out.println(date+" is After "+a+" : "+date.isAfter(a));
		System.out.println(date+" is Before "+b+" : "+date.isAfter(b));
		System.out.println("Length of month : "+date.lengthOfMonth());
		System.out.println("Length of the year : "+date.lengthOfYear());
		System.out.println("23 days added : "+date.plusDays(23));
		System.out.println("2 weeks added : "+date.plusWeeks(2));
		System.out.println("4 years added : "+date.plusYears(4));
		
		
		System.out.println("50 days minused : "+date.minusDays(50));
        System.out.println("11 months minused : "+date.minusMonths(11));
        System.out.println("5 years minused : "+date.minusYears(5));

       

        System.out.println("100 hours added : "+time.plusHours(100));
        System.out.println("1000 minutes added : "+time.plusMinutes(1000));
        System.out.println("100000 seconds added : "+time.plusSeconds(100000));
        System.out.println("1000000 nono seconds added : "+time.plusNanos(1000000));
	}
}
