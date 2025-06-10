package Bst;

import java.util.LinkedList;
import java.util.Queue;

public class BstGen<T extends Comparable <T>> {
		NodeGen<T> root;
		public BstGen() {
			root=null;
		}
		public void insert(T value) {
			root=this.insertKey(root,value);
		}
		public  NodeGen<T> insertKey(NodeGen<T> root,T value) {
			if(root==null) {
				root=new NodeGen<T>(value);
				return root;
			}
			if(value.compareTo(root.value)<0) {
				root.left=insertKey(root.left,value);
			}
			else {
				root.right=insertKey(root.right,value);
			}
			return root;
		}
		public void preorder() {
			this.preorderTraversal(root);
			System.out.println("\n");
		}
		public void postorder() {
			this.postorderTraversal(root);
			System.out.println("\n");
		}
		public void inorder() {
			this.inorderTraversal(root);
			System.out.println("\n");
		}
		public void levelorder() {
			Queue<NodeGen<T>> queue=new LinkedList<>();
			System.out.println("Levelorder Traversal :");
			queue.add(root);
			this.levelorderTraversal(queue);
		}
		public  void levelorderTraversal(Queue<NodeGen<T>> queue) {
			NodeGen<T> node=(NodeGen<T>)queue.poll();
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
			this.levelorderTraversal(queue);		
		}
		public  void inorderTraversal(NodeGen<T> root) {
			if(root!=null) {
				this.inorderTraversal(root.left);
				System.out.print(root.value+" ");
				this.inorderTraversal(root.right);
			}
		}
		public void preorderTraversal(NodeGen<T> root) {
			if(root!=null) {
				System.out.print(root.value+" ");
				this.preorderTraversal(root.left);
				this.preorderTraversal(root.right);
			}
		}
		public void postorderTraversal(NodeGen<T> root) {
			if(root!=null) {
				this.postorderTraversal(root.left);
				this.postorderTraversal(root.right);
				System.out.print(root.value+" ");
			}
		}
		
		public static void main(String[] args) {
			BstGen<String> b=new BstGen<>();
			b.insert("Arjun");
			b.insert("Bharath");
			b.insert("Abhi");
			b.insert("Aravind");
			b.insert("Tharun");
			b.insert("Ravi");
			b.insert("Taneem");
			System.out.println("Inorder traversal : ");
			b.inorder();
			System.out.println("preorder traversal : ");
			b.preorder();
			System.out.println("postorder traversal : ");
			b.postorder();
			b.levelorder();
		}
	   
	}



