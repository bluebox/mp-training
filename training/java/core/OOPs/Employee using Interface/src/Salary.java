import java.time.LocalDate;

public interface Salary {
	public default void retire(String endDate,boolean isRetired) {
		if(LocalDate.parse(endDate).compareTo(LocalDate.now())>=0) {
			System.out.println("It is too early to be retired");
		}
		else {
			isRetired=true;
			System.out.println("Congratulations! and thanks for your work with us");
		}
	}
}
