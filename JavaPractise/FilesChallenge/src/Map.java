import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Map {
	public static void main(String[]Lists) {
		HashMap<String,List<Integer>> map =new HashMap<>();	
		map.put("Odd",new ArrayList<>());
		map.put("Even",new ArrayList<>());
		for(int i=0;i<=100;i++) {
			if(i%2==0) {
				 map.get("even").add(i);
			}
			else {
			        map.get("odd").add(i);
			}
		}
		ArrayList<Integer> odd = map.get("odd");
		ArrayList<Integer> even=map.get("even");
		System.out.println("The Odd numbers are :"+odd);
		System.out.println("The even numbers are :"+even);
	}

}
