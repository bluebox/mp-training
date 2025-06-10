package challanges;

import java.util.*;
import java.util.Scanner;

class Node{
	public int data;
	Node left,right;
	
	Node(int val){
		data=val;
		left=right=null;
	}
}
public class LevelOrderOfTree {
	
	public static Node insertNode(Node root,int data) {
		if(root==null) {
			return new Node(data);
		}
		else if(data<root.data) {
			root.left=insertNode(root.left,data);
		}
		else {
			root.right=insertNode(root.right, data);
		}
		return root;
	}
	            
	public static void levelOrderOfTree(Node root) {
		if(root==null)
			return;
		Queue<Node> que=new LinkedList<>();
		que.add(root);
		
		while(!que.isEmpty()) {
			Node tmpNode = que.poll();
			
			System.out.print(tmpNode.data+" ");
			
			if(tmpNode.left != null) {
				que.add(tmpNode.left);
			}
			if(tmpNode.right != null) {
				que.add(tmpNode.right);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the no of nodes : ");
		int n=sc.nextInt();
		int[] tree=new int[n];
		System.out.print("Enter the nodes : ");
		for(int i=0;i<n;i++) {
			tree[i]=sc.nextInt();
		}
		Node root=null;
		for(int i=0;i<n;i++) {
			root=insertNode(root,tree[i]);
		}
		System.out.println("Tree data : "+Arrays.toString(tree));
		System.out.println("Level order of tree : ");
		levelOrderOfTree(root);
		
	}

}

