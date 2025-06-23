package learn2;

//import java.util.ArrayList;
//import java.util.Arrays;

import java.util.*;

public class CreationOfList {
	
	public static void main(String[] args) {
		
		
		
		
		
		
		
		
		System.out.println(".......................");
		Integer a[] = new Integer[]{1,2,3,4,5,6}; 
		List<Integer> list = Arrays.asList(a);
		ArrayList<Integer> arr = new ArrayList<>(list);
		List<Integer> l = (List) arr;
		arr.add(20);
		arr.addAll(list);
		System.out.println(list);
		System.out.println(arr);
		System.out.println(l);
		
		
		System.out.println(".......................");
		Integer num = 9;
		int num1 = 20;
		System.out.println(num.getClass());
		num1 = num;
		System.out.println(num);
		System.out.println(Integer.SIZE);
		System.out.println(num.getClass());
		System.out.println(num.equals(num1));
	}
}
