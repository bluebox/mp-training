package Weekproblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class Dailyproblem_bst {
	
	
	public static void main(String[] args) {
		
		Node root=new Node(50);
		bst(root,new Node(35));
		bst(root,new Node(65));
		bst(root,new Node(30));
		bst(root,new Node(17));
		bst(root,new Node(60));
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
	public static void bst(Node root,Node node) {
		
		if(root==null||node==null) {
			return;
		}
		
		if(node.key>root.key) {
			if(root.right!=null) {
				bst(root.right,node);
			}
			else {
			root.right=node;}
		}
		else {
			if(root.left!=null)
				bst(root.left,node);
			else {
			root.left=node;}
		}
		
	}
	

}
