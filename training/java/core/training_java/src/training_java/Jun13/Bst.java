package Bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bst {
	Node root;
	int count=0;
	public Bst() {
		root = null;
	}

	public void insert(int value) {
		root = insertKey(root, value);
	}

	public static Node insertKey(Node root, int value) {
		if (root == null) {
			root = new Node(value);
			return root;
		}
		if (root.value > value) {
			root.left = insertKey(root.left, value);
		} else {
			root.right = insertKey(root.right, value);
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
		Queue<Node> queue = new LinkedList<>();
		System.out.println("levelorder Traversal :");
		queue.add(root);
		levelorderTraversal(queue);
	}

	public static void levelorderTraversal(Queue<Node> queue) {
		Node node = (Node) queue.poll();
		if (node == null) {
			return;
		}
		System.out.print(node.value + " ");
		if (node.left != null) {
			queue.offer(node.left);
		}
		if (node.right != null) {
			queue.offer(node.right);
		}
		levelorderTraversal(queue);
	}
	public void findSumForward(Node root,int value, int targetSum) {
		if (root == null) {
			return;
			}
		if (value+root.value==targetSum) {
			System.out.println("count incremented in findsum zone"+root.value+" "+value);
			count=count+1;
		}
		
		findSumForward(root.left,value+root.value,targetSum);
		findSumForward(root.right,value+root.value,targetSum);
	}
	public Integer targetSum(Node root,int value,int targetSum) {
		if(root==null) {
			return null;
		}
		if(root.value==value) {
			findSumForward(root,0,targetSum);
			return root.value;
		}
		Integer leftSum=targetSum(root.left,value,targetSum);
		if(leftSum!=null) {
			if(root.value+leftSum==targetSum) {
				System.out.println("count incremented in tzone"+root.value+" "+leftSum);
				count=count+1;
			}
			findSumForward(root.right,root.value+leftSum,targetSum);
			return leftSum+root.value;
		}
		Integer rightSum=targetSum(root.right,value,targetSum);
		if(rightSum!=null) {
			if(root.value+rightSum==targetSum) {
				System.out.println("count incremented in tzone");
				count=count+1;
			}
			

			findSumForward(root.left,root.value+rightSum,targetSum);

			return rightSum+root.value;
		}
		return null;
	}
	public void findTargetSum(int value,int targetSum) {
		Integer k=targetSum(root,value,targetSum);
		System.out.println("Count is : "+count);
	}
	public void findNodeForward(Node root, int k, List<Integer> lis) {
		if (root == null||k<0) {
			return;
		}
		if (k == 0) {
			lis.add(root.value);
			return;
		}
		findNodeForward(root.left,k-1,lis);
		findNodeForward(root.right,k-1,lis);
	}
	public int kDistNodes(Node root,int k,int value,List<Integer> lis) {
		if(root==null) {
			return -1;
		}
		if(root.value==value) {
			findNodeForward(root,k,lis);
			return 1;
		}
		int leftDepth=kDistNodes(root.left,k,value,lis);
		if(leftDepth!=-1) {
			if(k-leftDepth==0) {
				lis.add(root.value);
			}
			else {
				findNodeForward(root.right,k-leftDepth-1,lis);
			}
			return leftDepth+1;
		}
		int rightDepth=kDistNodes(root.right,k,value,lis);
		if(rightDepth!=-1) {
			if(k-rightDepth==0) {
				lis.add(root.value);
			}
			else {
				findNodeForward(root.left,k-rightDepth-1,lis);
			}
			return rightDepth+1;
		}
		return -1;
	}
	public void kDistanceNode(int value,int distance) {
		List<Integer> lis=new ArrayList<Integer>();
		kDistNodes(root,distance,value,lis);
		Collections.sort(lis);
		System.out.println();
		System.out.println("The distance nodes for "+value+" at distance "+distance);
		System.out.println(lis);
	}

	public static void inorderTraversal(Node root) {
		if (root != null) {
			inorderTraversal(root.left);
			System.out.print(root.value + " ");
			inorderTraversal(root.right);
		}
	}

	public static void preorderTraversal(Node root) {
		if (root != null) {
			System.out.print(root.value + " ");
			preorderTraversal(root.left);
			preorderTraversal(root.right);
		}
	}

	public static void postorderTraversal(Node root) {
		if (root != null) {
			postorderTraversal(root.left);
			postorderTraversal(root.right);
			System.out.print(root.value + " ");
		}
	}

	public static void main(String[] args) {
		Bst b = new Bst();
		b.insert(10);
		b.insert(5);
		b.insert(15);
		b.insert(3);
		b.insert(7);
		b.insert(12);
		b.insert(18);
		b.insert(2);
		b.insert(4);
		b.insert(6);
		b.insert(8);
		b.insert(-2);
		b.insert(1);
		System.out.println("Inorder traversal : ");
		b.inorder();
		System.out.println("preorder traversal : ");
		b.preorder();
		System.out.println("postorder traversal : ");
		b.postorder();
		b.levelorder();
		b.kDistanceNode(50,2);
		b.findTargetSum(5,8);
	}

}
