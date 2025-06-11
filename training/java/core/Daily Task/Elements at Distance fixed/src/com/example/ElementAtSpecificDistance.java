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
		dist(t,dist);
		return l;
		
	}
	public static void dist(Tree t,int dist){
		if(t==null || t.b==false) {
			return;
		}
		System.out.println((t.data!=0)?t.data:"null"+" "+((t.left!=null)?t.left.data:"null")+" "+((t.right!=null)?t.right.data:"null")+" "+((t.parent!=null)?t.parent.data:"null"));
		
		t.b=false;
		if(dist==0) {
			l.add(t.data);
			return;
		}
		dist(t.left,dist-1);
		dist(t.right,dist-1);
		dist(t.parent,dist-1);
	}
}
