package sets;


import java.util.HashSet;
import java.util.List;

public class HashSetExample {
	public static void main(String[] args) {
		HashSet<Integer> set=new HashSet<>();
		set.addAll(List.of(23,45,34,23,67,4,2,3,4));
		System.out.println(set);
		System.out.println(set.contains(23));
		HashSet<Integer> set2=new HashSet<>(List.of(23,45,34,23,67,4,2,3,4));
		System.out.println(set.equals(set2));
		
		System.out.println((set.getClass().getSimpleName()));
		
		set.remove(23);
		System.out.println(set);
		System.out.println(set.size());
		System.out.println(set.isEmpty());
		
								

	}

}
