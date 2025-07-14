package July8;

import java.time.ZoneId;

public class ZonesPractice {

	public static void main(String[] args) {
		
		System.out.println(ZoneId.systemDefault());
		System.out.println("Number of Time Zones = " + ZoneId.getAvailableZoneIds().size());
		ZoneId.getAvailableZoneIds().stream()
		.filter(s -> s.startsWith("Asia"))
		.sorted()
		.limit(17)
		.forEach(System.out::println);
		
	}
}
