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
	
//	Level Oder
	public static void adjacentNodes(Node root,int t,int d) {
		ArrayList<Integer> res = new ArrayList<>();
		getDistance(root,t,d,res);		
		if(res.size()==0) {
			System.out.println("There are no nodes for given distance "+d+" for Target node "+t);
		}
		else {
			System.out.println("There are nodes for given distance "+d+" for Target node "+t+" : "+res);
		}
	}
	
	public static int getDistance(Node root,int t,int d,ArrayList<Integer> res) {
		if(root==null)
			return -1;
		if(root.data==t) {
			findNodes(root,d,res);
			return 1;
		}
		
		int left = getDistance(root.left, t, d, res);
		if(left!=-1) {
			if(d-left==0) {
				res.add(root.data);
			}
			else {
				findNodes(root.right,d-left-1,res);
			}
			return left+1;
		}
		
		int right = getDistance(root.right, t, d, res);
		if(right!=-1) {
			if(d-right==0) {
				res.add(root.data);
			}
			else {
				findNodes(root.left,d-right-1,res);
			}
			return right+1;
		}
		return -1;
	}
	
	public static void findNodes(Node root,int d,ArrayList<Integer> res) {
		if(root==null)
			return;
		if(d==0) {
			res.add(root.data);
			return;
		}
		findNodes(root.left,d-1,res);
		findNodes(root.right,d-1,res);
	}
	
//	Target Sum Pairs
	
	public static int targetSumPairs(Node root,int tarSum) {
		Set<Integer> seen = new HashSet<>();
		return dfsTargetSum(root,tarSum,seen);
	}
	
	public static int dfsTargetSum(Node root,int tarSum,Set<Integer> seen) {
		int counter = 0;
		if(root==null)
			return counter;
		if(seen.contains(tarSum-root.data)) {
			counter++;
		}
		seen.add(root.data);
		counter += dfsTargetSum(root.left, tarSum, seen);
		counter += dfsTargetSum(root.right, tarSum, seen);
		return counter;
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
		System.out.println("Level order Traversal : ");
		levelOrderOfTree(root);
		System.out.print("Enter the target node : ");
		int t=sc.nextInt();
		System.out.print("Enter  the distance : ");
		int d=sc.nextInt();
		adjacentNodes(root,t,d);
		System.out.print("Enter the targetSum : ");
		int tarSum = sc.nextInt();
		int pairs = targetSumPairs(root,tarSum);
		System.out.println("Node pairs which are equal to target sum "+tarSum+" are : "+pairs);
		
	}

}
