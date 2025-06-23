package com.collections;

import java.util.ArrayList;
import java.util.ListIterator;

public class Listitem {
	
	public static void main(String[] args) {
		
		ArrayList<String> list= new ArrayList<>();
		ArrayList<String> list2= new ArrayList<>(5);
		
		list.add("Hello");
		list.add("World");
		
		System.out.println(list);
		
		list.add(1,"Hii");
		list.add(3,"Regular");
		
		System.out.println(list);
		list2=list;
		
		list.addAll(list2);
		System.out.println(list);
		
		list.remove(3);
		System.out.println(list);
		
		list.addAll(2, list2);
		System.out.println(list);
		
		list2.retainAll(list);
		System.out.println(list2);
		
		
		list.set(6, "Hello");
		System.out.println(list);
		
		
		list.remove(9);
		System.out.println(list);
		
		System.out.println(list.size());
		list2.trimToSize();
		System.out.println(list2);
		
		
		
		
		
		
		ListIterator<String> l= list.listIterator();
		while(l.hasNext()) {
			System.out.println(l.next());
		}
		
	}
}
