package day7;

import java.util.HashMap;
import java.util.Map;

public class MapMain {

	public static void main(String[] args) {
		Map<String, String> address = new HashMap<>();
		address.put("rohit", "mumbai");
		address.put("virat", "delhi");
		address.put("dhoni", "ranchi");
		for (Map.Entry<String, String> pair : address.entrySet()) {
			System.out.printf("%s's home is %s\n", pair.getKey(), pair.getValue());
		}
	}

}
