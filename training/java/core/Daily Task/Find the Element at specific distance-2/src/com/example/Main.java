package com.example;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		Tree t=Tree.binTree(5,2,1,4,3,7,8);
		int x=dist(t,2,1);
	}
	
	public static int dist(Tree t,int val,int d) {
		if(t==null) {
			return -1;
		}
		if(t.data==val) {
			traverse(t, d);
			return d-1;
		}
		if(d==0) {
			System.out.println(t.data);
			return 50000;
		}
		if(dist(t.left, val,d)>0) {
			if(dist(t.left, val, d)==0) {
				System.out.println(t.data);
				return 5000;
			}
			traverse(t.right, d-1);
		}
		if(dist(t.right, val,d)>=0) {
			if(dist(t.right, val,d)==0) {
				System.out.println(t.data);
				return 5000;
			}
			traverse(t.left, d-1);
		}
		return 0;
	}
	public static void traverse(Tree t,int dist) {
		if(t==null) {
			return ;
		}
		
		else {
			if(dist==0) {
				System.out.println(t.data);
			}
			else {
				traverse(t.left, dist-1);
				traverse(t.right, dist-1);
			}
		}
//		System.out.println();
//		System.out.print(t.data+"--");
	}
}
