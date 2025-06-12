import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;

public class Main {
	
	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		
		//Usage of YearMonth and Month	
		YearMonth date = YearMonth.now();
		System.out.printf("Date :%s ,\nNumber of days in this month: %d;\n\n", date, date.lengthOfMonth());
		
		YearMonth date2 = YearMonth.of(2025, Month.FEBRUARY);
		System.out.printf("Date :%s ,\nNumber of days in this month: %d;\n\n", date2, date2.lengthOfMonth());

		YearMonth date3 = YearMonth.of(2024, Month.FEBRUARY);
		System.out.printf("Date :%s ,\nNumber of days in this month: %d;\n\n", date3, date3.lengthOfMonth());
		
		
		//Usage of LocalDateTime
		System.out.printf("now: %s%n", LocalDateTime.now());

		System.out.printf("Apr 15, 1994 @ 11:30am: %s\n",
		                  LocalDateTime.of(1994, Month.APRIL, 15, 11, 30));

		System.out.printf("\nnow (from Instant): %s\n",
		                  LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));

		System.out.printf("\n6 months from now: %s\n",
		                  LocalDateTime.now().plusMonths(6)); 
		
		System.out.printf("\n6 months ago: %s\n",
		                  LocalDateTime.now().minusMonths(6));
		
		
	}

}
