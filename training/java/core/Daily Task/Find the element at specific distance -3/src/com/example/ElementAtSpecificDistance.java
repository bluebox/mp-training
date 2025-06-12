package com.example;

import java.util.ArrayList;

public class ElementAtSpecificDistance {
	public static int count=0;
	public static ArrayList<Integer> l1=new ArrayList<Integer>();
	public static ArrayList<Integer> l2=new ArrayList<Integer>();
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
		return l1;
	}
	public static int elesum(Tree t,int val,int sum) {
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
		sum(t,0,sum);
		return count;
	}
	public static void dist(Tree t,int dist){
		if(t==null || Tree.l.contains(t.data) || dist<0) {
			return;
		}
		Tree.l.add(t.data);
		if(dist==0) {
			l1.add(t.data);
//			System.out.println(t.data);
			return;
		}
		dist(Tree.h.get(t.data),dist-1);
		dist(t.left,dist-1);
		dist(t.right,dist-1);
		
	}
	public static void sum(Tree t,int sum,int val) {
		if(t==null || l2.contains(t.data)) {
			return;
		}
		System.out.println(t.data+" "+sum+" "+val);
		l2.add(t.data);
		if(sum==val) {
			count++;
//			System.out.println(t.data);
			return;
		}
		sum(Tree.h.get(t.data),sum+t.data,val);
		sum(t.left,sum+t.data,val);
		sum(t.right,sum+t.data,val);
	}
}