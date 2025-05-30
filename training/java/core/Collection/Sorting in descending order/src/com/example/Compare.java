package com.example;
import java.util.Comparator;

public class Compare implements Comparator{
	public int compare(Object a,Object b) {
		int x=(int) a;
		int y=(int) b;
		if(x>y) {
			return -1;
		}
		else if(x==y) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
