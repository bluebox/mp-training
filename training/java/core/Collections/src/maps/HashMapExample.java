package maps;

import java.util.HashMap;

public class HashMapExample {
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 56);
		map.put(2, 45);
		map.put(3, 89);
		map.put(4, 12);
		map.put(5, 43);
		System.out.println(map);
		System.out.println(map.containsValue(4));
		map.putIfAbsent(3, 90);
		System.out.println(map);
		System.out.println(map.getOrDefault(6, 90));
		map.remove(2);
		System.out.println(map);
		System.out.println(map.values());
		System.out.println(map.keySet());
		map.forEach((a,b)->{
			System.out.print(b+" ");
			
		});
		map.clear();
		System.out.println(map);

	}

}
