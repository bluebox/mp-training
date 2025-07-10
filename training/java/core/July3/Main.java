package July3;

import java.util.HashMap;
import java.util.Map;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class Main {
	public static void main(String[] args) {
		
		Map<Day, Integer> mp =new HashMap<>();
		mp.put(Day.MONDAY, 100);
		mp.put(Day.TUESDAY, 102);
		mp.put(Day.MONDAY,  mp.get(Day.MONDAY)+1);
		
		System.out.println(mp);
		
	}
}
