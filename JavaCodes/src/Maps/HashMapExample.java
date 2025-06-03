package Maps;

import java.util.*;
// this store the key and values and this are in unordered way that is not sorted;
public class HashMapExample {

	public static void main(String[] args) {
		Map<Integer,Integer>mp=new HashMap<>();
		List<Integer>ans=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=0;i<n;i++) {
			ans.add(sc.nextInt());
		}
		for(Integer ele:ans) {
		    mp.put(ele,mp.getOrDefault(ele, 0)+1);
		}
		System.out.println("map Traversal using entry set");
		for(Map.Entry<Integer, Integer> entry:mp.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		System.out.println("using keySet");
		for(Integer ele:mp.keySet()) {
			System.out.println(ele+" "+mp.get(ele));
		}
	}

}
