package Bst;

import java.util.LinkedList;
import java.util.Queue;

public class Bst {
	Node root;
	public Bst() {
		root=null;
	}
	public void insert(int value) {
		root=insertKey(root,value);
	}
	public static Node insertKey(Node root,int value) {
		if(root==null) {
			root=new Node(value);
			return root;
		}
		if(root.value>value) {
			root.left=insertKey(root.left,value);
		}
		else {
			root.right=insertKey(root.right,value);
		}
		return root;
	}
	public void preorder() {
		preorderTraversal(root);
		System.out.println("\n");
	}
	public void postorder() {
		postorderTraversal(root);
		System.out.println("\n");
	}
	public void inorder() {
		inorderTraversal(root);
		System.out.println("\n");
	}
	public void levelorder() {
		Queue<Node> queue=new LinkedList<>();
		System.out.println("levelorder Traversal :");
		queue.add(root);
		levelorderTraversal(queue);
	}
	public static void levelorderTraversal(Queue<Node> queue) {
		Node node=(Node) queue.poll();
		if(node==null) {
			return;
		}
		System.out.print(node.value+" ");
		if(node.left!=null) {
			queue.offer(node.left);
		}
		if(node.right!=null) {
			queue.offer(node.right);
		}
		levelorderTraversal(queue);		
	}
	public static void inorderTraversal(Node root) {
		if(root!=null) {
			inorderTraversal(root.left);
			System.out.print(root.value+" ");
			inorderTraversal(root.right);
		}
	}
	public static void preorderTraversal(Node root) {
		if(root!=null) {
			System.out.print(root.value+" ");
			preorderTraversal(root.left);
			preorderTraversal(root.right);
		}
	}
	public static void postorderTraversal(Node root) {
		if(root!=null) {
			postorderTraversal(root.left);
			postorderTraversal(root.right);
			System.out.print(root.value+" ");
		}
	}
	
	public static void main(String[] args) {
		Bst b=new Bst();
		b.insert(50);
		b.insert(30);
		b.insert(20);
		b.insert(40);
		b.insert(70);
		b.insert(60);
		b.insert(80);
		System.out.println("Inorder traversal : ");
		b.inorder();
		System.out.println("preorder traversal : ");
		b.preorder();
		System.out.println("postorder traversal : ");
		b.postorder();
		b.levelorder();
	}
   
}
