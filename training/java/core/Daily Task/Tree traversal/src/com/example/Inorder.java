package com.example;

public class Inorder {
	public static void inOrder(Tree t) {
		if(t!=null && t.left!=null) {
			inOrder(t.left);
		}
		if(t!=null) {
			System.out.print(t.root + " ");
		}
		if(t.right!=null) {
			inOrder(t.right);
		}
	
	}
}
