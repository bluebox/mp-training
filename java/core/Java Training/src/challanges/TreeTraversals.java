package challanges;
import java.util.*;

class Node{
	public int data;
	Node left,right;
	
	Node(int val){
		data=val;
		left=right=null;
	}
}
public class TreeTraversals {
	
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
	            
	public static void preOrder(Node root) {
		if(root!=null) {
			System.out.print(root.data+"  ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	
	public static void inOrder(Node root) {
		if(root!=null) {
			inOrder(root.left);
			System.out.print(root.data+"  ");
			inOrder(root.right);
		}
	}
	
	public static void postOrder(Node root) {
		if(root!=null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.print(root.data+"  ");
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
		System.out.println("Pre order tree traversal : ");
		preOrder(root);
		System.out.println();
		System.out.println("In order tree traversal : ");
		inOrder(root);
		System.out.println();
		System.out.println("Post order tree traversal : ");
		postOrder(root);
	}

}
