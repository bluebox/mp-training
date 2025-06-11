package com.example;
public class BinaryTree {
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
			}
			binaryTree(t.left,i);
		}
		else {
			if(t.right==null) {
				t.right=new Tree(i);
				return ;
			}
			binaryTree(t.right,i);
		}
	}
}
