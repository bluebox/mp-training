package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;

public class AnyMatch {

	public static void main(String[] args) {
		List<String> fruits=Arrays.asList("AppLe","BaNaNa","CanBerry","BlueBerry","blackApple");
		boolean isPresent=fruits.stream().anyMatch(s->s.toLowerCase().startsWith("b"));
		if(isPresent) {
			System.out.println("Some of the fruits name starts with 'b'");
		}
		else {
			System.out.println("No fruit name starts with 'b'");
		}
		
	}

}
