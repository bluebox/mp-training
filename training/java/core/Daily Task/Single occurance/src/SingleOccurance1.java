package com.example;
import java.util.ArrayList;
import java.util.List;

public class SingleOccurance1 {
	public static void main(String[] args) {
		ArrayList<Integer> l=new ArrayList<>(List.of(4,4,11,2,11,2,3,3,11,3));
		int x=0,y=0,z=0;
		for(int i:l) {
			x^=i;
		}
		int a=2;
		while((x%2)==0) {
			a*=2;
			x/=2;
		}
		for(int i:l) {
			if((i%a)>=(a/2)) {
				y^=i;
			}
			else {
				z^=i;
			}
		}
		System.out.println(y+" "+z);
	}
}
