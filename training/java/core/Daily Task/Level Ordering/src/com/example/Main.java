package com.example;

public class Main {
	public static void main(String[] args) {
		Tree b=BinaryTree.binTree(2,5,4,6,3,6,4,7,6,9,8,6);
		LevelOrdering l=new LevelOrdering();
		l.levelOrdering(b);
	}

}
