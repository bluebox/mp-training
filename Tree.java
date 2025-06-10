package package_1;
import java.util.*;

public class Tree {
	Node head;
	private Queue<Node> q=new LinkedList<Node>();
	public Node add_node(Node head,int val) {
		if (head==null) {
			head=new Node(val);
		}
		else if(head.value>val) {
			head.left=add_node(head.left,val);
		}
		else {
			head.right=add_node(head.right,val);
		}
		return head;
	}
	public void addNode(int val) {
		head=this.add_node(head,val);
		
	}
		
	public void preorder(Node root) {
		if(root!=null) {
			System.out.print(root.value+",");
			preorder(root.left);
			preorder(root.right);
	}

	}
	public void postorder(Node root) {
		if(root!=null) {
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.value+",");
	}
	}
	public void inorder(Node root) {
		if(root!=null) {
			inorder(root.left);
			System.out.print(root.value+",");
			inorder(root.right);
	}
	}
	public void print_level_order(Node root) {
		if(root!=null) {
		if(root.left!=null) {
		q.add(root.left);}
		if(root.right!=null){
		q.add(root.right);
		}
		if (this.q.size()!=0) {
		Node new_root=this.q.remove();
		System.out.print(new_root.value+",");
		print_level_order(new_root);}}


		
	}
	
	public void level_order() {
		System.out.println("Level Order");
		System.out.print(this.head.value+",");
		print_level_order(this.head);
	}
	public void Printpreorder() {
		System.out.println("Preorder Traversal: ");
		preorder(this.head);
	}
	public void Printpostorder() {
		System.out.println("Postorder Traversal: ");

		postorder(this.head);
		
	}
	public void Printinorder() {
		System.out.println("inorder Traversal: ");

		inorder(this.head);
		
	}

		
}