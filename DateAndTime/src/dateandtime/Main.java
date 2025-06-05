package dateandtime;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Main {
	public static void main(String[] args) {
		LocalDate tdy=LocalDate.now();
		System.out.println(tdy);
		System.out.println(tdy.get(ChronoField.YEAR));
		System.out.println(tdy.getDayOfYear());
		System.out.println(tdy.getMonth());
		System.out.println(tdy.getEra());
		System.out.println(tdy.getEra());
		
		LocalDate nov19=LocalDate.parse("2002-11-19");
		System.out.println(nov19);
		System.out.println(nov19.getEra());
		System.out.println(nov19.getDayOfYear());
		
		System.out.println(nov19.compareTo(tdy));
		System.out.println(tdy.compareTo(nov19));
		
		System.out.println(ChronoUnit.DAYS.between(nov19, tdy));
		
		LocalDate mar16=LocalDate.of(2004, 3, 16);
		System.out.println(ChronoUnit.DAYS.between(mar16, tdy));
		System.out.println(ChronoUnit.DAYS.between(nov19, mar16));
		System.out.println(nov19.isBefore(mar16));
		System.out.println(mar16.isBefore(mar16));
		System.out.println(mar16.isBefore(nov19));
		
		//LocalDate sai=new LocalDate(2004, 6, 20); constructor is private
		

		
		
		
	}

}
