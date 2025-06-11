package com.example;

import java.util.ArrayList;

public class ElementAtSpecificDistance {
	static ArrayList<Integer> l=new ArrayList<Integer>();
	public static ArrayList<Integer> eleDistance(Tree t,int val,int dist){
		while(t!=null) {
			if(t.data>val) {
				t=t.left;
			}
			else if(t.data<val){
				 t=t.right;
			}
			else {
				break;
			}
		}
		System.out.println();
		dist(t,dist);
		return l;
		
	}
	public static void dist(Tree t,int dist){
		if(t==null || t.b==false || dist<0) {
			return;
		}
		t.b=false;
		if(dist==0) {
			l.add(t.data);
			System.out.println(l);
			return;
		}
		dist(t.parent,dist-1);
		dist(t.left,dist-1);
		dist(t.right,dist-1);
		
	}
}