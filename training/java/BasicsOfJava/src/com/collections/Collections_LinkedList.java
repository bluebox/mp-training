package com.collections;

import java.util.LinkedList;

public class Collections_LinkedList {
	
	public static void main(String[] args) {
		
		LinkedList<String> list = new LinkedList<>();
		LinkedList<String> list1;
		
		list.add("one");
		list.add("two");
		System.out.println(list);
		
		list.addFirst("First");
		list.addLast("Last");
		System.out.println(list);
		
		list1 = (LinkedList<String>) list.clone(); 
		System.out.println(list1);
		list1.clear();
		System.out.println(list1);
		
		
		
	}
}
