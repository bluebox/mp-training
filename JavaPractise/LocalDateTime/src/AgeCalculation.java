import java.time.LocalDate;
import java.time.Period;

public class AgeCalculation {
	public static void main(String[] args) {
		LocalDate birthDay=LocalDate.of(2004, 9, 3);
		LocalDate today=LocalDate.now();
		Period p=Period.between(birthDay, today);
		System.out.println(p.getYears()+" Years "+p.getMonths()+" Months "+p.getDays()+" Days");
	}
}
