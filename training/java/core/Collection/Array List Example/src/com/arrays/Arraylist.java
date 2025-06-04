package com.arrays;
import java.util.*;

public class Arraylist {
	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<>();
		a.add(5);
		a.add(8);
		a.add(7);
		System.out.println(a);
		a.add(1, 3);
		System.out.println("After adding 3 at the index of 1: "+a);
		a.addAll(List.of(2,4,5,3,6,4,7));
		System.out.println("After adding a list to original list: "+a);
		ArrayList<Integer> b = a;
		a.add(5);
		System.out.println("a is : "+a);
		System.out.println("b is : "+b);
		b=(ArrayList<Integer>) a.clone();
		a.add(3);
		System.out.println("a is :"+a);
		System.out.println("b is :"+b);
		System.out.println("a contains4? : " +a.contains(4));
		System.out.println("a contains [4,3,4] : "+a.containsAll(List.of(4,3,4)));
		System.out.println("a contains [4,3,4,9] : "+a.containsAll(List.of(4,3,4,9)));
		System.out.println(a.get(5));
		a.forEach(System.out::println);
		System.out.println(a);
		a.remove(3);
		System.out.println("a after removing element 3 : "+a);
		//if objects in the collection is number then when we remove using remove(i) remove objects at index i
		System.out.println("is a empty? : "+a.isEmpty());
		System.out.println("is a empty? : "+new ArrayList<>().isEmpty());
		System.out.println("First occurance of 3 in a : "+ a.indexOf(3));
		System.out.println("Last occurance of 3 in a : "+ a.lastIndexOf(3));
		System.out.println(a.toString());
	}
}
