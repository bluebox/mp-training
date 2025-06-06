package com.example;

public class PreOrder {
	public static void preOrder(Tree t) {
		if(t!=null) {
			System.out.print(t.root+" ");
		}
		if(t.left!=null) {
			preOrder(t.left);
		}
		if(t.right!=null) {
			preOrder(t.right);
		}
	}
}
