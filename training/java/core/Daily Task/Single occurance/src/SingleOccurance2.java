package com.example;
import java.util.ArrayList;
import java.util.Collections;

public class SingleOccurance2 {
	public static void main(String[] args) {
		ArrayList<Integer> l=new ArrayList<>();
		l.add(4);
		l.add(2);
		l.add(2);
		l.add(3);
		Collections.sort(l);
		int x=0;
		int y=1;
		for(int i:l) {
			if((y%i)==0) {
				x-=i;
				y/=i;
			}
			else {
				x+=i;
				y*=i;
			}
		}
		System.out.println((x+((int)(Math.sqrt((x*x)-(4*y)))))/2);
		System.out.println((x-((int)(Math.sqrt((x*x)-(4*y)))))/2);
	}
}
