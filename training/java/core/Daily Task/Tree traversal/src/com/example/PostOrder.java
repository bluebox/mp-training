package com.example;

public class PostOrder {
	public static void postOrder(Tree t) {
		if(t!=null && t.left!=null) {
			postOrder(t.left);
		}
		if(t!=null && t.right!=null) {	
			postOrder(t.right);
		}
		if(t!=null) {
			System.out.print(t.root+" ");
		}
	}
}
