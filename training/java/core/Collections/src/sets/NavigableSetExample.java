package sets;

import java.util.HashSet;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableSetExample {
	public static void main(String[] args) {
		NavigableSet<Integer> ns=new TreeSet<>(List.of(45,23,67,30));
		ns.add(12);
		System.out.println(ns);

		System.out.println(ns.lower(66));
		System.out.println(ns.floor(66));
		System.out.println(ns.higher(10));
		System.out.println(ns.ceiling(10));
		System.out.println(ns.pollFirst());
		System.out.println(ns);
		System.out.println(ns.pollLast());
		System.out.println(ns);	
		ns.addAll(List.of(67,78,4,6,3));
		System.out.println(ns.add(4));
		System.out.println(ns.first());
		System.out.println(ns.last());
		System.out.println(ns.remove(1));	
		System.out.println(ns.remove(23));
		
		NavigableSet<Integer> dns=ns.descendingSet();
		System.out.println(dns);
		dns.add(23);
		System.out.println(dns);
		
	}

}
