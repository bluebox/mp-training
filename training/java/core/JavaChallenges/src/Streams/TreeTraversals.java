package Streams;

class Node
{
	Node left;
	Node right;
	int  data;
	Node(int data)
	{
		this.data=data;
		left=null;
		right=null;
	}
}
public class TreeTraversals {
	Node root=null;
	public void addNode(int data)
	{
		if(root==null)
		{
			root=new Node(data);
			root.left=null;
			root.right=null;
		}
		else
		{
			root=addNode(root,data);
		}
		
	}
	public Node addNode(Node root,int data)
	{
		if(root==null)
		{
			return new Node(data);
		}
		if(root.data>data )
		{
			root.left=addNode(root.left,data);
		}
		else
		{
			root.right=addNode(root.right,data);
		}
		return root;
	}
	public void inOrder(Node root)
	{
		if(root==null)
		{
			return;
		}
		inOrder(root.left);
		System.out.print(root.data+" ");
		inOrder(root.right);
	}
	public void preOrder(Node root)
	{
		if(root==null)
		{
			return;
		}
		System.out.print(root.data+" ");
		preOrder(root.left);
		preOrder(root.right);
	}
	public void postOrder(Node root)
	{
		if(root==null)
		{
			return;
		}
		postOrder(root.left);
		postOrder(root.right);
		System.out.print(root.data+" ");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeTraversals r=new TreeTraversals();
		r.addNode(50);
		r.addNode(30);
		r.addNode(20);
		r.addNode(40);
		r.addNode(70);
		r.addNode(60);
		r.addNode(80);
		System.out.println("Inorder Traversal:");
		r.inOrder(r.root);
		System.out.println();
		System.out.println("PostOrder Traversal:");
		r.postOrder(r.root);
		System.out.println();
		System.out.println("PreOrder Traversal:");
		r.preOrder(r.root);
		
		
	}

}
