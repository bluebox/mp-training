import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;

public class Localdates {
	public static void getDateMonthYear(String date)
	{
		LocalDate currentDate=LocalDate.parse(date);
		int day=currentDate.getDayOfMonth();
		Month month=currentDate.getMonth();
		int year=currentDate.getYear();
		System.out.println("Day: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String date="2025-07-08";
		getDateMonthYear(date);
		LocalDate May5=LocalDate.of(2005, 05, 05);
		System.out.println("Year changes "+May5.withYear(2000));
		System.out.println("Month changes "+May5.withMonth(3));
		System.out.println("Day changes acording to month "+May5.withDayOfMonth(4));
		System.out.println("Day Changes according to year "+May5.withDayOfYear(126));
		System.out.println("Date is "+May5);
		System.out.println(May5.with(ChronoField.DAY_OF_YEAR,126));
		System.out.println("After adding Years "+May5.plusYears(5));
		System.out.println("After adding months "+May5.plusMonths(12));
		System.out.println("After adding weeks "+May5.plusWeeks(52));
		System.out.println(LocalDate.now());
	}

}

