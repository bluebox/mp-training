package Maps;

import java.util.*;

public class TreeMapExample {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		List<Integer>ans=new ArrayList<>();
		Map<Integer,Integer>mp=new TreeMap<>();
		for(int i=0;i<n;i++) {
		   ans.add(sc.nextInt());
		}
		for(Integer ele:ans) {
			mp.put(ele,mp.getOrDefault(ele, 0)+1);
		}
		for(Map.Entry<Integer, Integer> entry:mp.entrySet()) {
			System.out.println(entry.getKey()+" "+entry.getValue());
		}
		for(Integer ele:mp.keySet()) {
			System.out.println(ele+" "+mp.get(ele));
		}
	}

}
