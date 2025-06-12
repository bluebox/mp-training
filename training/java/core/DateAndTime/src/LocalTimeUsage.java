import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.concurrent.TimeUnit;

public class LocalTimeUsage {
	public static void main(String[] args) throws InterruptedException {
		LocalTime current = LocalTime.now();
		System.out.println(current);
		// TimeUnit.SECONDS.sleep(5);
		int hours = (int) TimeUnit.SECONDS.toHours(3670);
		System.out.println(current.get(ChronoField.CLOCK_HOUR_OF_DAY));
		System.out.println("Minutes " + current.getMinute());

		System.out.println(hours);
	}

}
