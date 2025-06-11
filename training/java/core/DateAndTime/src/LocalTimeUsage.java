import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class LocalTimeUsage {
	public static void main(String[] args) throws InterruptedException {
		LocalTime current=LocalTime.now();
		System.out.println(current);
			//TimeUnit.SECONDS.sleep(5);
		int hours=(int) TimeUnit.SECONDS.toHours(3670);
			
		System.out.println(hours);
	}

}
