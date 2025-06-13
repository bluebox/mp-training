package Maps;

import java.util.*;
import java.util.stream.IntStream;

public class TreeMapNavigable {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		List<Integer>ans=new ArrayList<>();
		IntStream.range(0, n)
            .forEach(i->ans.add(sc.nextInt()));
        System.out.println();
       NavigableMap<Integer,Integer>mp=new TreeMap<>();
       for(Integer ele:ans) {
    	   mp.put(ele,mp.getOrDefault(ele, 0)+1);
       }
       System.out.println("using entryset");
       for(Map.Entry<Integer, Integer> entry:mp.entrySet()) {
    	   System.out.println(entry.getKey()+" "+entry.getValue());
       }
       System.out.println("using keyset");
       for(Integer ele:mp.keySet()) {
    	   System.out.println(ele+" "+mp.get(ele));
       }
       // in navigable we have several extra function like floor and ceiling 
       // Returns the greatest key ≤ the given key (i.e., the key itself or the one just below it).
       System.out.println(mp.floorKey(5));
       // Returns the smallest key ≥ the given key (i.e., the key itself or the one just above it).
       System.out.println(mp.ceilingKey(10));
       // navigable is only applicable for treeMap that is navigable is only valid for ordered maps
	}
}
