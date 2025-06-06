package com.example;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Tree x = BinaryTree.binTree(9,2,4,3,5,4,6,5,7,8);
		System.out.println("Inorder of given tree is : ");
		Inorder.inOrder(x);
		System.out.println();
		System.out.println("PreOrder of given tree is : ");
		PreOrder.preOrder(x);
		System.out.println();
		System.out.println("PostOrder of given tree is : ");
		PostOrder.postOrder(x);
	}
}
