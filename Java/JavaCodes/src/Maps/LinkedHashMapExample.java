package Maps;

import java.util.*;
import java.util.Map.Entry;

// this store the elements by their insertion order
public class LinkedHashMapExample {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		List<Integer>ans=new ArrayList<>();
		for(int i=0;i<n;i++) {
			ans.add(sc.nextInt());
		}
		Map<Integer,Integer>mp=new LinkedHashMap<>();
		for(Integer ele:ans) {
			mp.put(ele,mp.getOrDefault(ele,0)+1);
		}
		for(Map.Entry<Integer, Integer> entry:mp.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		for(Integer ele:mp.keySet()) {
			System.out.println(ele+" "+mp.get(ele));
		}
	}

}
