package Weekproblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node{
	int key;
	Node left;
	Node right;
	public Node(int key) {
		this.key=key;
		left=right=null;
	}
	
	
}

public class Problem {
	
	
public static void main(String[] args) {
	Node root=new Node(5);
	Node l1=new Node(6);
	root.left=l1;
	Node r1=new Node(7);
	root.right=r1;
	l1.left=new Node(18);
	l1.right=new Node(20);
	r1.left=new Node(24);
	r1.right=new Node(33);
	r1.left.right=new Node(44);
	l1.left.right=new Node(55);
	levelorder(root);
	
}
public static void levelorder(Node root) {
		if(root==null) {
			return ;
		}
		Queue<Node> queue=new LinkedList<>();
		queue.add(root);
		List<Integer> list=new ArrayList<>();
		while(queue.size()>0) {
			
			Node node=queue.poll();
			list.add(node.key);
			if(node.left!=null) {
				queue.add(node.left);
			}
			if(node.right!=null) {
				queue.add(node.right);
			}
		}
		System.out.println(list);
}
}
