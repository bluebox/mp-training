package day_14_07_2025;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node<T>{
	T data;
	Node<T> left;
	Node<T> right;
	
	public Node(T data) {
		this.data=data;
		this.left=null;
		this.right=null;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", left=" + left + ", right=" + right + "]";
	}
	
	
	
	
}

public class BinaryTree {

	public static void main(String[] args) {
		Node<Object> root=new Node<>(12);
		//System.out.println(root.toString());
        root.left=new Node<Object>(120);
        root.right=new Node<Object>(130);
        root.left.left=new Node<Object>(123);
        root.left.right=new Node<Object>(124);
        root.right.left=new Node<Object>(154);
        root.right.right=new Node<Object>(165);
        List<Integer> d=new ArrayList<>();
        d.add(23);
        root.right.right.left=new Node<Object>(d);
        // Above Binary Tree representation
        //               12
        //            /      \
       //            /        \
        //         120          130
        //        /    \       /   \
         //      /      \     /     \
        //      123    124   154    165
        //
        //
        
        
        // Level Order Traversal of a binary tree 
        Queue<Node<Object>> q=new LinkedList<>();
        q.add(root);
        ArrayList<Object> list=new ArrayList<>();
        while(!q.isEmpty()) {
        	int size=q.size();
            Node<Object> temp=q.poll();
        		list.add(temp.data);
        		if(temp.left != null) {
        			q.add(temp.left);
        		}
        		if(temp.right != null) {
        			q.add(temp.right);
        		}
        	}
         
        System.out.println(list);
    	   
       }
        
	}

