package com.example;

import java.util.HashMap;

public class Main {
	public static void main(String[] args) {
		Tree t=Tree.binTree(5,2,1,4,3,7,8);
		HashMap<Integer,Tree> h=new HashMap<Integer, Tree>();
		
	}
	
	public void dist(Tree t,int d,String dir) {
		if(t==null) {
			return;
		}
		if(d==0) {
			System.out.println(t.data);
		}
		else {
			if(dir!="left") {
				dist(t.left,d-1, dir);
			}
			if(dir!="right") {
				dist(t.right,d-1, dir);
			}
		}
	}
}
