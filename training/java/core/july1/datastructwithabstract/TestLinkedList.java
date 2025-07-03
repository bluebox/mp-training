package com.tulasidhar.july1.datastructwithabstract;

public class TestLinkedList {
	public static void main(String[] args) {
		MyLinkedList ll = new MyLinkedList();
		ll.addItem(new Node(1));
		ll.addItem(new Node(2));
		ll.addItem(new Node(23289));
		ll.addItem(new Node(49));
		ll.traverse();
		
		System.out.println(ll.removeItem(new Node(2)));
		ll.traverse();
		System.out.println("____________________________________________________");
		SearchTree st = new SearchTree();
		st.addItem(new Node(1));
		st.addItem(new Node(2));
		st.addItem(new Node(23289));
		st.addItem(new Node(49));
		st.traverse();
		
		System.out.println(st.removeItem(new Node(2)));
		st.traverse();
		
	}
}
