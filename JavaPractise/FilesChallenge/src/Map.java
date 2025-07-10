import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

	public static void main(String[]Lists) {
		HashMap<String,List<Integer>> map =new HashMap<>();
		
//		map.put("Odd",new ArrayList<>());
//		map.put("Even",new ArrayList<>());
		for(int i=0;i<=100;i++) {
			if(i%2==0) {
				List<Integer> even = map.getOrDefault("even", new ArrayList<>());
				even.add(i);
				map.putIfAbsent("even", even);
			}
			else {
				List<Integer> odd = map.getOrDefault("odd", new ArrayList<>());
				odd.add(i);
				map.putIfAbsent("odd", odd);
			}
		}

	}

}
