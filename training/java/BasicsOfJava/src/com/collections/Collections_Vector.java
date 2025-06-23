package com.collections;
import java.util.Vector;

public class Collections_Vector {

	public static void main(String[] args) {
		
		Vector<Integer> list = new Vector<>(3,2);
		
		System.out.println(list);
		list.add(1);
		list.add(2);
		list.add(2);
		
		System.out.println(list);
		
		list.insertElementAt(3, 1);
		list.remove(3);
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.capacity());
		list.setSize(3);
		
		System.out.println(list.size());
		System.out.println(list.capacity());
		System.out.println(list.subList(0, 2));
		System.out.println(list.toString());
		
	}
}
