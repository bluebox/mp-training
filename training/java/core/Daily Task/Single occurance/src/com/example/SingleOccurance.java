package com.example;
import java.util.*;

public class SingleOccurance {
	public static void main(String[] args) {
		ArrayList<Integer> l=new ArrayList<>(List.of(2,4,3,4,2,6,5,6));
		Map<Integer,Integer> m= new HashMap<>();
		for(int i:l) {
			if(m.containsKey(i)) {
				m.put(i,m.get(i)+1);
			}
			else {
				m.put(i,1);
			}
		}
		for(int i:m.keySet()) {
			if(m.get(i)==1) {
				System.out.println(i);
			}
		}
	}
}
