package Streams;

import java.util.*;

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
class Pair
{
	Node node;
	int d;
	Pair(Node node,int d)
	{
		this.node=node;
		this.d=d;
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
	public void LevelOrder(Node root)
	{
		if(root==null)
		{
			return;
		}
		Queue<Node> pq=new LinkedList<>();
		pq.offer(root);
		while(pq.size()>0)
		{
			int s=pq.size();
			for(int i=0;i<s;i++)
			{
				Node n=pq.poll();
				System.out.print(n.data+" ");
				if(n.left!=null)
				{
					pq.offer(n.left);
				}
				if(n.right!=null)
				{
					pq.offer(n.right);
				}
			}
		}
	}
	public void kDistanceNodes(Node root,int val,int k)
	{
		HashMap<Node,Node> hm=new HashMap<>();
		Queue<Node> q1=new LinkedList<>();
		Queue<Pair> q2=new LinkedList<>();
		q1.offer(root);
		List<Integer> l=new ArrayList<>();
		while(q1.size()>0)
		{
			int s=q1.size();
			for(int i=0;i<s;i++)
			{
				Node p=q1.poll();
				if(p.data==val)
				{
					q2.offer(new Pair(p,0));
				}
				if(p.left!=null)
				{
					hm.put(p.left, p);
					q1.offer(p.left);
				}
				if(p.right!=null)
				{
					hm.put(p.right, p);
					q1.offer(p.right);
				}
			}
		}
		Set<Node> s=new HashSet<>();
		while(q2.size()>0)
		{
			Pair p=q2.poll();
			Node n=p.node;
			if(p.d==k)
			{
				l.add(p.node.data);
			}
			if(p.d>k)
			{
				continue;
			}
			if(n.left!=null && !s.contains(n.left))
			{
				q2.offer(new Pair(n.left,p.d+1));
				s.add(n.left);
			}
			if(n.right!=null && !s.contains(n.right))
			{
				q2.offer(new Pair(n.right,p.d+1));
				s.add(n.right);
			}
			if(hm.get(n)!=null && !s.contains(hm.get(n)))
			{
				q2.offer(new Pair(hm.get(n),p.d+1));
				s.add(hm.get(n));
			}
		}
		System.out.println(l);
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
		System.out.println();
		System.out.println("LevelOrder Traversal:");
		r.LevelOrder(r.root);
		System.out.println();
		System.out.println("k Distance Neighbours:");
		r.kDistanceNodes(r.root,30,3);
		
	}

}
