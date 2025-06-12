package com.example;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		Tree t=Tree.binTree(5,2,1,4,3,7,8);
		HashMap<Integer,Tree> h=new HashMap<Integer, Tree>();
		
	}
	
	public int dist(Tree t,int val,int d) {
		if(t==null) {
			return -1;
		}
		if(t.data==val) {
			return d;
		}
		if(d==0) {
			System.out.println(t.data);
		}
		else {
			if(dist(t.left, val, d)>0) {
				int d1 = dist(t.right, val, d);
			}
		}
		return 0;
	}
}
