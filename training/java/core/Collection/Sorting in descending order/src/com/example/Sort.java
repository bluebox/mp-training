package com.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
	public static void main(String[] args) {
		Comparator c=new Compare();
		ArrayList<Integer> l=new ArrayList<>(List.of(2,6,4,7,6,3));
		Collections.sort(l, c);
		System.out.println(l);
		
	}
}
