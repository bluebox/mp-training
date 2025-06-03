package maps;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
	public static void main(String[] args) {
		LinkedHashMap<Integer,String> map=new LinkedHashMap<>();
		map.put(3,"mani");
		map.put(2, "sai");
		map.put(1, "manoj");
		System.out.println(map);
		System.out.println(map.get(2));
		
		System.out.println(map.containsKey(2));
		System.out.println(map.containsKey(5));
		System.out.println(map.firstEntry());
		System.out.println(map.remove(2));
		System.out.println(map);
		

	}

}
