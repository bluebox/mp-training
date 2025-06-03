package com.example;

import java.util.ArrayList;
import java.util.List;

public class ArraySum {
	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<>(List.of(4,6,5,7,3,6,3,66,34,655,6,3,6,34,66,5));
		int sum=0;
		for(int i:a) {
			sum+=i;
		}
		System.out.println("Sum of elements in the array "+a+" is: "+sum);
		sum=a.stream().reduce(0,(x,y)->x+y);
		System.out.println("Sum of elements in the array "+a+" is: "+sum);
		sum=a.stream().reduce(0,Integer::sum);
		System.out.println("Sum of elements in the array "+a+" is: "+sum);
		sum=a.stream().filter(i->(i%2==0)).reduce(0,Integer::sum);
		System.out.println("Sum of even numbers in the array "+a+" is: "+sum);
		sum=a.stream().filter(i->((i%2)!=0)).reduce(0,Integer::sum);
		System.out.println("Sum of odd numbers in the array "+a+" is: "+sum);
	}
}
