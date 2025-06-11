package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree {
	public int data;
	public Tree parent;
	public Tree left;
	public Tree right;
	public boolean b;
	public Tree(int data) {
		this.data=data;
		this.parent=null;
		this.left=null;
		this.right=null;
		this.b=true;
	}
	public static Tree binTree(int ...args) {
		Tree t=new Tree(args[0]);
		for(int i=1;i<args.length;i++)
		{
			binaryTree(t,args[i]);
		}
		return t;
	}
	public static void binaryTree(Tree t,int i) {
		if(t.data>i) {
			if(t.left==null) {
				t.left=new Tree(i);
				t.left.parent=t;
				return;
			}
			binaryTree(t.left,i);
		}
		else {
			if(t.right==null) {
				t.right=new Tree(i);
				t.right.parent=t;
				return ;
			}
			binaryTree(t.right,i);
		}
	}
}