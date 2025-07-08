package dev.tulasidhar.presonalpractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComapratorTestMain {
	public static void main(String[] args) {
		
		List<Integer> list = new ArrayList<Integer>(); 
		list.add(234);
		list.add(40);
		list.add(1);
		list.add(120);
		
		//another way to create an arraylist
		List<Integer> list1 = Arrays.asList(1,2,3,4,5);
		
		
		System.out.println(list);
		
		//normally sorting without custom comparator
		Collections.sort(list);
		
		System.out.println(list);
		
		
		//
		
	}
}
