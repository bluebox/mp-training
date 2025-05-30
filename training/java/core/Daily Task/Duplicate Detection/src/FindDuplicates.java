package com.example;

import java.util.*;

public class FindDuplicates {
	public static void main(String[] args) {
		ArrayList<Integer> l=new ArrayList<>(List.of(3,5,4,5,3,2,1)); 
		Set<Integer> s=new HashSet<>();
		int x=0;
		for(int i:l) {
			if(!s.add(i)){
				if(x==2) {
					break;
				}
				System.out.println(x+"st duplicate number is: "+i);
				x+=1;
			}
		}
	}
}
