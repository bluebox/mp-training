package Package1;

public class Tree {
	Node head;
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
