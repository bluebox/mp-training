package Bst;

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
	}
   
}
